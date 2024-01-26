package com.onetuks.happyparkingserver.auth.jwt;

import static com.onetuks.happyparkingserver.auth.model.vo.RoleType.USER;

import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthTokenProvider {

    @Value("${jwt.accessTokenExpiryPeriod}")
    private long accessTokenExpiryPeriod;

    @Value("${jwt.refreshTokenExpiryPeriod}")
    private long refreshTokenExpiryPeriod;

    private final Key key;

    public AuthTokenProvider(@Value("${jwt.tokenSecretKey}") String secretKey) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public AuthToken provideAccessToken(String socialId, Long memberId) {
        return new AuthToken(socialId, memberId, USER.getRoleName(), getExpiryDate(accessTokenExpiryPeriod), key);
    }

    public AuthToken provideRefreshToken() {
        return new AuthToken(getExpiryDate(refreshTokenExpiryPeriod), key);
    }

    public AuthToken convertToAuthToken(String token) {
        return new AuthToken(token, key);
    }

    private Date getExpiryDate(long expiryPeriod) {
        return new Date(System.currentTimeMillis() + expiryPeriod);
    }

}
