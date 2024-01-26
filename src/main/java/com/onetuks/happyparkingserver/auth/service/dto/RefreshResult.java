package com.onetuks.happyparkingserver.auth.service.dto;

public record RefreshResult(
        String accessToken,
        Long userId
) {

    public static RefreshResult of(String accessToken, Long memberId) {
        return new RefreshResult(accessToken, memberId);
    }
}
