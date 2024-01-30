package com.onetuks.happyparkingserver.util.mock_user;

import com.onetuks.happyparkingserver.auth.jwt.CustomUserDetails;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithMockUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {

    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser annotation) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        List<SimpleGrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority(annotation.authorities().getRoleName()));

        CustomUserDetails customUserDetails = CustomUserDetails.builder()
                .memberId(annotation.memberId())
                .socialId(annotation.socialId())
                .authorities(authorities)
                .build();

        Authentication authentication = new UsernamePasswordAuthenticationToken(customUserDetails, "CREDENTIAL", authorities);

        context.setAuthentication(authentication);
        return context;
    }

}
