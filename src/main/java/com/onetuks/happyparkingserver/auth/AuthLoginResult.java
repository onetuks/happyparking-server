package com.onetuks.happyparkingserver.auth;

public record AuthLoginResult(
//        String appToken,
        boolean isNewMember,
        long memberId,
        String name
) {

    public static AuthLoginResult of(boolean isNewMember, Long memberId, String nickname) {
        return new AuthLoginResult(isNewMember, memberId, nickname);
    }

}
