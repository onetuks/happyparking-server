package com.onetuks.happyparkingserver.auth.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import com.onetuks.happyparkingserver.auth.exception.SecurityExceptionHandlerFilter;
import com.onetuks.happyparkingserver.auth.jwt.JwtAuthenticationFilter;
import java.util.stream.Stream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final CorsConfig corsConfig;
    private final SecurityExceptionHandlerFilter securityExceptionHandlerFilter;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(
            CorsConfig corsConfig,
            SecurityExceptionHandlerFilter securityExceptionHandlerFilter,
            JwtAuthenticationFilter jwtAuthenticationFilter
    ) {
        this.corsConfig = corsConfig;
        this.securityExceptionHandlerFilter = securityExceptionHandlerFilter;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(request -> request
                        .requestMatchers(
                                Stream.of(AuthPermitedEndpoint.ENDPOINTS)
                                        .map(AntPathRequestMatcher::antMatcher)
                                        .toArray(AntPathRequestMatcher[]::new)
                        ).permitAll()
                        .anyRequest().authenticated())
                .cors(cors -> corsConfig.corsConfigurationSource())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(securityExceptionHandlerFilter, JwtAuthenticationFilter.class)
                .build();
    }

}
