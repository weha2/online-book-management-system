package com.weha.online_book_management_system.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TokenUtil {

    @Value("${app.token.secret}")
    private String secret;

    @Value("${app.token.issuer}")
    private String issuer;

    @Value("${app.token.expiration}")
    private Long expiration;

    private Algorithm algorithm() {
        return Algorithm.HMAC256(secret);
    }

    public String createToken(String principle, String role) {
        Date now = new Date();
        Date expire = new Date(now.getTime() + (expiration * 1000));
        return JWT.create()
                .withIssuer(issuer)
                .withClaim("principle", principle)
                .withClaim("role", role)
                .withExpiresAt(expire)
                .sign(algorithm());
    }

    public DecodedJWT decodedJWT(String token) {
        JWTVerifier verifier = JWT.require(algorithm())
                .withIssuer(issuer)
                .build();
        return verifier.verify(token);
    }

    public String getPrinciple() throws Exception {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication.getPrincipal().equals("anonymousUser")) {
            throw new Exception("Invalid token.");
        }
        return authentication.getPrincipal().toString();
    }

    public String getRole() {
        SecurityContext context = SecurityContextHolder.getContext();
        List<String> roles = new ArrayList<>();
        context.getAuthentication().getAuthorities().forEach(s -> roles.add(s.getAuthority()));
        return roles.getFirst();
    }

}
