package com.onetuks.happyparkingserver.auth;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class ClientProviderStrategyHandler {

    private final Map<ClientProvider, ClientProviderStrategy> strateties = new ConcurrentHashMap<>();

    private final KakaoClientProviderStrategy kakaoClientStrategy;
    private final GoogleClientProviderStrategy googleClientStrategy;

    public ClientProviderStrategyHandler(
            KakaoClientProviderStrategy kakaoClientStrategy,
            GoogleClientProviderStrategy googleClientStrategy
    ) {
        this.kakaoClientStrategy = kakaoClientStrategy;
        this.googleClientStrategy = googleClientStrategy;

        this.strateties.put(ClientProvider.KAKAO, kakaoClientStrategy);
        this.strateties.put(ClientProvider.GOOGLE, googleClientStrategy);
    }

    public ClientProviderStrategy getClientStrategy(ClientProvider provider) {
        return strateties.get(provider);
    }

}
