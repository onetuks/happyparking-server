package com.onetuks.happyparkingserver.auth.util;

import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MemberIdResolverConfig implements WebMvcConfigurer {

    private final MemberIdResolver resolver;

    public MemberIdResolverConfig(MemberIdResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(resolver);
    }
}
