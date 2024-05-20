package com.tom.service;

import org.springframework.stereotype.Service;

import com.tom.bean.LoginRequest;
import com.tom.bean.LoginResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {

	@Value("${security.jwt.key}")
    private String jwtSecretKey;

    @Value("${security.access-token-ttl-seconds}")
    private int accessTokenTtlSeconds;

    private Key secretKey;
    private JwtParser jwtParser;


    @PostConstruct
    private void init() {
        secretKey = Keys.hmacShaKeyFor(this.jwtSecretKey.getBytes());
        jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();
    }

    public LoginResponse createToken(LoginRequest request) {
        String accessToken = createAccessToken(request.getUsername());

        LoginResponse res = new LoginResponse();
        res.setAccessToken(accessToken);
        return res;
    }

    private String createAccessToken(String username) {
        // 有效時間（毫秒）
        long expirationMillis = Instant.now()
                .plusSeconds(this.accessTokenTtlSeconds)
                .getEpochSecond()
                * 1000;

        // 設置標準內容與自定義內容
        Claims claims = Jwts.claims();
        claims.setSubject("Access Token");
        claims.setIssuedAt(new Date());
        claims.setExpiration(new Date(expirationMillis));
        claims.put("username", username);

        // 簽名後產生 token
        return Jwts.builder()
                .setClaims(claims)
                .signWith(secretKey)
                .compact();
    }

    private String createRefreshToken(String username) {
        long expirationMillis = Instant.now()
                .plusSeconds(600)
                .getEpochSecond()
                * 1000;

        Claims claims = Jwts.claims();
        claims.setSubject("Refresh Token");
        claims.setIssuedAt(new Date());
        claims.setExpiration(new Date(expirationMillis));
        claims.put("username", username);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(secretKey)
                .compact();
    }

    public String refreshAccessToken(String refreshToken) {
        Map<String, Object> payload = parseToken(refreshToken);
        String username = (String) payload.get("username");
        System.out.println(username);
        return createAccessToken(username);
    }

    public Map<String, Object> parseToken(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();
        return new HashMap<>(claims);
    }
	
}
