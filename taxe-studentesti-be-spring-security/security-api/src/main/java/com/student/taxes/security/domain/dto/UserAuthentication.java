package com.student.taxes.security.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserAuthentication implements Authentication {

    private List<GrantedAuthority> authorities;
    private String credentials;
    private String name;
    private boolean authenticated;
    private UserDetails principal;

    public UserAuthentication(Authentication auth) {
        this.authorities = (List<GrantedAuthority>) auth.getAuthorities();
        this.credentials = (String) auth.getCredentials();
        this.name = auth.getName();
        this.authenticated = auth.isAuthenticated();
        this.principal = (UserDetails) auth.getPrincipal();
    }

    @Override
    public Object getDetails() {
        return null;
    }
}
