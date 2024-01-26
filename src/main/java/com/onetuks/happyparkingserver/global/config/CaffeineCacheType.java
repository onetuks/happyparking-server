package com.onetuks.happyparkingserver.global.config;

import lombok.Getter;

@Getter
public enum CaffeineCacheType {
    AUTH_TOKEN_CACHE("authTokenCache", 30);

    private static final int MAXIMUM_CACHE_SIZE = 10000;

    private final String cacheName;
    private final int expireAfterWrite;
    private final int maximumSize;

    CaffeineCacheType(String cacheName, int expireAfterWrite) {
        this.cacheName = cacheName;
        this.expireAfterWrite = expireAfterWrite;
        this.maximumSize = MAXIMUM_CACHE_SIZE;
    }

}
