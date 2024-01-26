package com.onetuks.happyparkingserver.auth.exception;

import com.onetuks.happyparkingserver.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class TokenExpiredException extends IllegalStateException {

    private final ErrorCode errorCode;

    public TokenExpiredException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
