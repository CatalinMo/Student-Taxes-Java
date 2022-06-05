package com.student.taxes.security.filter;

import com.student.taxes.security.application.JwtTokenProvider;
import com.student.taxes.security.application.TokenCacheService;
import com.student.taxes.security.domain.dto.AuthTokenDto;
import com.student.taxes.security.domain.dto.UserAuthentication;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorizationFilter extends OncePerRequestFilter {

    private static final String BEARER_TOKEN = "Bearer ";
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenCacheService tokenCacheService;

    @Override
    public void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = extractToken(httpServletRequest);
        if (token != null) {
            filterJwtToken(token);
        }
        chain.doFilter(httpServletRequest, response);
    }

    private void filterJwtToken(String token) {
        if (tokenCacheService.isTokenValid(token)) {
            UserAuthentication auth = tokenCacheService.getAuthentication(token);
            SecurityUtils.getSubject().login(auth);
        } else if (jwtTokenProvider.validateToken(token)) {
            UserAuthentication auth = jwtTokenProvider.getAuthentication(token);
            AuthTokenDto authToken = new AuthTokenDto(token, new UserAuthentication(auth));
            tokenCacheService.addToken(authToken);
            SecurityUtils.getSubject().login(auth);
        }
    }

    public String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith(BEARER_TOKEN)) {
            return authHeader.replace(BEARER_TOKEN, "");
        }
        return null;
    }
}
