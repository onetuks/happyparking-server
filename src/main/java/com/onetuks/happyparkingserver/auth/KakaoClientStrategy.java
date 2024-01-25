package com.onetuks.happyparkingserver.auth;

import com.onetuks.happyparkingserver.global.error.ErrorCode;
import java.util.Objects;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class KakaoClientStrategy implements ClientStrategy {

    private final WebClient webClient;

    public KakaoClientStrategy(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Member getUserData(String accessToken) {
        KakaoUser kakaoUser = webClient.get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .headers(httpHeaders -> httpHeaders.set("Authorization", accessToken))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        clientResponse -> Mono.error(new TokenValidFailedException(ErrorCode.UNAUTHORIZED_TOKEN)))
                .onStatus(HttpStatusCode::is5xxServerError,
                        clientResponse -> Mono.error(
                                new TokenValidFailedException(ErrorCode.OAUTH_CLIENT_SERVER_ERROR)))
                .bodyToMono(KakaoUser.class)
                .block();

        return Member.of(
                Objects.requireNonNull(kakaoUser)
                        .getProperties()
                        .getNickname(),
                String.valueOf(kakaoUser.getId()),
                OAuth2Provider.KAKAO,
                RoleType.USER
        );
    }
}
