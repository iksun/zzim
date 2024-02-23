package com.sun.zzim.service.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class JwtTokenProvider {
    private final Key key;
    private static final String SECRET_KEY = "jwtsecretiksun132313jwtsecretiksun132313jwtsecretiksun132313jwtsecretiksun132313jwtsecretiksun132313";

    public JwtTokenProvider() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserDetail userDetail) {
        // 권한 가져오기
        String authorities = userDetail.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst().get();

        // Access Token 생성
        String accessToken = Jwts.builder()
                .setSubject(userDetail.getUsername())
                .claim("auth", authorities)
                .setExpiration(Timestamp.valueOf(LocalDateTime.now().plusDays(3)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return accessToken;
    }

}
