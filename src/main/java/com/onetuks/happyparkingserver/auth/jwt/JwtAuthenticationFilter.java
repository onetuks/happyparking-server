package com.onetuks.happyparkingserver.auth.jwt;

import com.onetuks.happyparkingserver.auth.service.AuthService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AuthTokenProvider tokenProvider;
    private final AuthService authService;

    public JwtAuthenticationFilter(AuthTokenProvider tokenProvider, AuthService authService) {
        this.tokenProvider = tokenProvider;
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(
            @Nonnull HttpServletRequest request,
            @Nonnull HttpServletResponse response,
            @Nonnull FilterChain filterChain
    ) throws ServletException, IOException {
        SecurityContext context = SecurityContextHolder.getContext();

        if (context.getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = AuthHeaderUtil.extractAuthToken(request);
        AuthToken authToken = tokenProvider.convertToAuthToken(accessToken);

        if (authToken.isValidTokenClaims() && !authService.isLogout(authToken.getToken())) {
            Authentication authentication = authToken.getAuthentication();
            context.setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

}
