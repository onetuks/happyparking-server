package com.onetuks.happyparkingserver.auth;

public record AuthRefreshResponse(
//        String appToken,
        long userId
) {

    public static AuthRefreshResponse from(AuthRefreshResult authRefreshResult) {
        return new AuthRefreshResponse(
//                authRefreshResult.appToken(),
                authRefreshResult.userId()
        );
    }

}
