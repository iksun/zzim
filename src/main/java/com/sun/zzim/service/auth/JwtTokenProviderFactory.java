package com.sun.zzim.service.auth;

import com.sun.zzim.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProviderFactory {
    private final JwtTokenProvider jwtTokenProvider;
    public JwtTokenProviderFactory(UserRepository userRepository, @Value("${jwt.secret.key}") String secretKey) {
        jwtTokenProvider = new JwtTokenProvider(userRepository, secretKey);
    }

    public JwtTokenProvider getJwtTokenProvider() {
        return jwtTokenProvider;
    }
}
