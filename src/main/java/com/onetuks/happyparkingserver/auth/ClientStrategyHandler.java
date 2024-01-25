package com.onetuks.happyparkingserver.auth;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class ClientStrategyHandler {

    private final Map<OAuth2Provider, ClientStrategy> strateties = new ConcurrentHashMap<>();

    private final KakaoClientStrategy kakaoClientStrategy;
    private final GoogleClientStrategy googleClientStrategy;

    public ClientStrategyHandler(KakaoClientStrategy kakaoClientStrategy, GoogleClientStrategy googleClientStrategy) {
        this.kakaoClientStrategy = kakaoClientStrategy;
        this.googleClientStrategy = googleClientStrategy;

        this.strateties.put(OAuth2Provider.KAKAO, kakaoClientStrategy);
        this.strateties.put(OAuth2Provider.GOOGLE, googleClientStrategy);
    }

    public ClientStrategy getClientStrategy(OAuth2Provider provider) {
        return strateties.get(provider);
    }

}
