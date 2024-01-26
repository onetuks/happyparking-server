package com.onetuks.happyparkingserver.auth;

import static org.springframework.http.HttpStatus.OK;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/auth")
public class AuthRestController {

    private static final String HEADER_AUTHORIZATION = "Authorization";

    private final OAuth2ClientService oAuth2ClientService;

    public AuthRestController(OAuth2ClientService oAuth2ClientService) {
        this.oAuth2ClientService = oAuth2ClientService;
    }

    @PostMapping(path = "/kakao")
    public ResponseEntity<AuthLoginResponse> kakaoLogin(HttpServletRequest request) {
        AuthLoginResult authLoginResult = oAuth2ClientService.login(
                ClientProvider.KAKAO
        );

        return ResponseEntity
                .status(OK)
                .body(AuthLoginResponse.from(authLoginResult));
    }

    @PostMapping(path = "/google")
    public ResponseEntity<AuthLoginResponse> googleLogin(HttpServletRequest request) {
        AuthLoginResult authLoginResult = oAuth2ClientService.login(
                ClientProvider.GOOGLE
        );

        return ResponseEntity
                .status(OK)
                .body(AuthLoginResponse.from(authLoginResult));
    }

//    @DeleteMapping(path = "/logout")
//    public ResponseEntity<AuthLogoutResponse> logout(HttpServletRequest request) {
//
//    }
//
//    @PutMapping(path = "/refresh")
//    public ResponseEntity<AuthRefreshResponse> refreshToken(HttpServletRequest request) {
//
//        return ResponseEntity
//                .status(OK)
//                .body(AuthRefreshResponse.from());
//    }

}
