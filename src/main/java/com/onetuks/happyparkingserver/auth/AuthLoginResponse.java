package com.onetuks.happyparkingserver.auth;

public record AuthLoginResponse(
//        String appToken,
        boolean isNewMember,
        long memberId,
        String name
) {

    public static AuthLoginResponse from(AuthLoginResult authLoginResult) {
        return new AuthLoginResponse(
                authLoginResult.isNewMember(),
                authLoginResult.memberId(),
                authLoginResult.name()
        );
    }

}
