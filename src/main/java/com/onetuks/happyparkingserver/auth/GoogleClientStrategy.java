package com.onetuks.happyparkingserver.auth;

import static com.onetuks.happyparkingserver.global.error.ErrorCode.OAUTH_CLIENT_SERVER_ERROR;
import static com.onetuks.happyparkingserver.global.error.ErrorCode.UNAUTHORIZED_TOKEN;

import com.onetuks.happyparkingserver.global.error.ErrorCode;
import java.util.Objects;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class GoogleClientStrategy implements ClientStrategy {

    private final WebClient webClient;

    public GoogleClientStrategy(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Member getUserData(String accessToken) {
        GoogleUser googleUser = webClient.get()
                .uri("https://www.googleapis.com/oauth2/v3/userinfo")
                .headers(httpHeaders -> httpHeaders.set("Authorization", accessToken))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        clientResponse -> Mono.error(new TokenValidFailedException(UNAUTHORIZED_TOKEN)))
                .onStatus(HttpStatusCode::is5xxServerError,
                        clientResponse -> Mono.error(new TokenValidFailedException(OAUTH_CLIENT_SERVER_ERROR)))
                .bodyToMono(GoogleUser.class)
                .block();

        return Member.of(
                Objects.requireNonNull(googleUser)
                        .getName(),
                googleUser.getSub(),
                OAuth2Provider.GOOGLE,
                RoleType.USER
        );
    }
}
