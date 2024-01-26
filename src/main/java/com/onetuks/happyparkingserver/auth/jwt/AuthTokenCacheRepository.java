package com.onetuks.happyparkingserver.auth.jwt;

import static com.onetuks.happyparkingserver.global.config.CaffeineCacheType.AUTH_TOKEN_CACHE;

import java.util.Optional;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Repository;

@Repository
public class AuthTokenCacheRepository {

    private final Cache cache;

    public AuthTokenCacheRepository(CacheManager cacheManager) {
        this.cache = cacheManager.getCache(AUTH_TOKEN_CACHE.getCacheName());
    }

    public void save(String accessToken, String refreshToken) {
        cache.put(accessToken, refreshToken);
    }

    public void delete(String accessToken) {
        cache.evictIfPresent(accessToken);
    }

    public Optional<String> findRefreshToken(String accessToken) {
        String refreshToken = cache.get(accessToken, String.class);
        return Optional.ofNullable(refreshToken);
    }

}
