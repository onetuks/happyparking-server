package com.onetuks.happyparkingserver.auth.controller.dto;

import com.onetuks.happyparkingserver.auth.service.dto.RefreshResult;

public record RefreshResponse(
        String accessToken,
        long userId
) {

    public static RefreshResponse from(RefreshResult refreshResult) {
        return new RefreshResponse(
                refreshResult.accessToken(),
                refreshResult.userId()
        );
    }

}
