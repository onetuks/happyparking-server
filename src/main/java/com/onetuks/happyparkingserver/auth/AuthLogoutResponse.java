package com.onetuks.happyparkingserver.auth;

public record AuthLogoutResponse(
        boolean isLogout
) {

    public static AuthLogoutResponse from(AuthLogoutResult authLogoutResult) {
        return new AuthLogoutResponse(
                authLogoutResult.islogout()
        );
    }

}
