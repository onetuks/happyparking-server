package com.onetuks.happyparkingserver.auth;

public class AuthPermitedEndpoint {

    private AuthPermitedEndpoint() {
    }

    protected static final String[] ENDPOINTS = new String[]{
            "/",
            "/auth/kakao",
            "/error",
            "/docs/**"
    };
}
