package com.onetuks.happyparkingserver.auth.exception;

import com.onetuks.happyparkingserver.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class TokenIsLogoutException extends IllegalStateException {

    private final ErrorCode errorCode;

    public TokenIsLogoutException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
