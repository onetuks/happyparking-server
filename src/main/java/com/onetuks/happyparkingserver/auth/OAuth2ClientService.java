package com.onetuks.happyparkingserver.auth;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OAuth2ClientService {

    private final ClientProviderStrategyHandler clientProviderStrategyHandler;
    private final MemberRepository memberRepository;

    public OAuth2ClientService(ClientProviderStrategyHandler clientProviderStrategyHandler,
            MemberRepository memberRepository) {
        this.clientProviderStrategyHandler = clientProviderStrategyHandler;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public AuthLoginResult login(ClientProvider clientProvider) {
        ClientProviderStrategy clientProviderStrategy = clientProviderStrategyHandler.getClientStrategy(clientProvider);

        Member clientMember = clientProviderStrategy.getUserData();
        String socialId = clientMember.getSocialId();
        ClientProvider oAuth2Provider = clientMember.getClientProvider();

        Optional<Member> optionalMember = memberRepository.findBySocialIdAndClientProvider(socialId, oAuth2Provider);
        Member savedMember = optionalMember.orElseGet(() -> memberRepository.save(clientMember));

        return AuthLoginResult.of(
                optionalMember.isEmpty(),
                savedMember.getId(),
                savedMember.getNickname()
        );
    }

}
