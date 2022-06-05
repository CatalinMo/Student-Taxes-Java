package com.student.taxes.security.application;

import com.student.taxes.security.config.properties.JwtAuthProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;

import static java.util.stream.Collectors.joining;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtTokenProvider {

    private static final String AUTHORITIES_KEY = "roles";
    private final JwtAuthProperties jwtProperties;
    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        String secret = Base64.getEncoder().encodeToString(this.jwtProperties.getSecretKey().getBytes());
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(this.secretKey).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String createToken(Authentication authentication) {
        String username = authentication.getName();
        Claims claims = Jwts.claims().setSubject(username);
        addAuthorities(authentication, claims);
        return buildToken(claims);
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(this.secretKey).build().parseClaimsJws(token).getBody();
        String authoritiesClaim = claims.get(AUTHORITIES_KEY).toString();
        Collection<? extends GrantedAuthority> authorities = addGrantedAuthoritiesForAuthentication(authoritiesClaim);
        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public Collection<? extends GrantedAuthority> addGrantedAuthorities(Object authoritiesClaim) {
        if (authoritiesClaim == null) {
            return AuthorityUtils.NO_AUTHORITIES;
        } else {
            return AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesClaim.toString());
        }
    }

    private Collection<? extends GrantedAuthority> addGrantedAuthoritiesForAuthentication(String authoritiesClaim) {
        if (authoritiesClaim == null) {
            return AuthorityUtils.NO_AUTHORITIES;
        } else {
            return AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesClaim);
        }
    }

    private void addAuthorities(Authentication authentication, Claims claims) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (!authorities.isEmpty()) {
            String authKeys = authorities.stream().map(GrantedAuthority::getAuthority).collect(joining(","));
            claims.put(AUTHORITIES_KEY, authKeys);
        }
    }

    private String buildToken(Claims claims) {
        long currentTimeMillis = System.currentTimeMillis();
        long validity = currentTimeMillis + this.jwtProperties.getValidity();
        Date validFrom = new Date(currentTimeMillis);
        Date validUntil = new Date(validity);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(validFrom)
                .setExpiration(validUntil)
                .signWith(this.secretKey, SignatureAlgorithm.HS512)
                .compact();
    }
}
