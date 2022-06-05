package com.student.taxes.security.config;

import com.student.taxes.security.domain.dto.UserAuthentication;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class JwtTokenRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UserAuthentication;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        return ((UserAuthentication) token).getAuthenticationInfo("jwtTokenRealm");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return ((UserAuthentication.JwtPrincipalCollection) principals).getAuthorizationInfo();
    }
}
