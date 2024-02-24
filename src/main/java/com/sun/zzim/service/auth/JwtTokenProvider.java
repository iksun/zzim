package com.sun.zzim.service.auth;

import com.sun.zzim.repository.UserRepository;
import com.sun.zzim.repository.datamodel.UserDataModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class JwtTokenProvider {
    private final UserRepository userRepository;
    private final Key key;

    public JwtTokenProvider(UserRepository userRepository, String secretKey) {
        this.userRepository = userRepository;
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserDetail userDetail) {
        String authorities = userDetail.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .get();

        String accessToken = Jwts.builder()
                .setSubject(userDetail.getUsername())
                .claim("auth", authorities)
                .setExpiration(Timestamp.valueOf(LocalDateTime.now().plusDays(3)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return accessToken;
    }

    public String getToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    public boolean validate(String jwt) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
            if(validateExpiredDate(claims)) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public Authentication getAuthentication(String jwt) {
        String username = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();

        if(!StringUtils.hasText(username)) {
           return null;
        }

        UserDataModel user = userRepository.findByLoginId(username);
        if(user == null) {
            return null;
        }

        UserDetail userDetail = new UserDetail(user);
        return new UsernamePasswordAuthenticationToken(userDetail, userDetail.getPassword(), userDetail.getAuthorities());
    }

    private boolean validateExpiredDate(Jws<Claims> claims) {
        return claims.getBody().getExpiration().before(Timestamp.valueOf(LocalDateTime.now()));
    }
}
