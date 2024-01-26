package com.onetuks.happyparkingserver.auth;

import java.util.Objects;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class GoogleClientProviderStrategy implements ClientProviderStrategy {

    private final WebClient webClient;

    public GoogleClientProviderStrategy(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Member getUserData(
//            String accessToken
    ) {
        GoogleUser googleUser = webClient.get()
                .uri("https://www.googleapis.com/oauth2/v3/userinfo")
//                .headers(httpHeaders -> httpHeaders.set("Authorization", accessToken))
                .retrieve()
//                .onStatus(HttpStatusCode::is4xxClientError,
//                        clientResponse -> Mono.error(new TokenValidFailedException(UNAUTHORIZED_TOKEN)))
//                .onStatus(HttpStatusCode::is5xxServerError,
//                        clientResponse -> Mono.error(new TokenValidFailedException(OAUTH_CLIENT_SERVER_ERROR)))
                .bodyToMono(GoogleUser.class)
                .block();

        return Member.of(
                Objects.requireNonNull(googleUser)
                        .getName(),
                googleUser.getSub(),
                ClientProvider.GOOGLE,
                RoleType.USER
        );
    }
}
