package com.student.taxes.security.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserAuthentication implements AuthenticationToken {

    private String credentials;
    private String principal;
    private List<String> roles;

    public UserAuthentication(UserAuthentication auth) {
        this.principal = auth.getPrincipal();
        this.credentials = auth.getCredentials();
        this.roles = auth.getRoles();
    }

    public UserAuthentication(String principal, String credentials, List<String> roles) {
        this.principal = principal;
        this.credentials = credentials;
        this.roles = roles;
    }

    public AuthenticationInfo getAuthenticationInfo(String realmName) {
        return new JwtAuthenticationInfo(realmName);
    }

    public class JwtAuthenticationInfo implements AuthenticationInfo {

        private final PrincipalCollection principals;

        public JwtAuthenticationInfo(String realmName) {
            this.principals = new JwtPrincipalCollection(realmName);
        }

        @Override
        public PrincipalCollection getPrincipals() {
            return principals;
        }

        @Override
        public Object getCredentials() {
            return credentials;
        }
    }

    public class JwtPrincipalCollection extends SimplePrincipalCollection {

        public JwtPrincipalCollection(String realmName) {
            super(principal, realmName);
        }

        public AuthorizationInfo getAuthorizationInfo() {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.addRoles(roles);
            return info;
        }
    }
}
