package com.student.taxes.security.presentation;

import com.student.taxes.security.AuthorizationApi;
import com.student.taxes.security.domain.dto.TokenWrapperDto;
import com.student.taxes.security.exception.MissingAuthorizationException;
import com.student.taxes.security.application.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorizationController implements AuthorizationApi {

    private final AuthorizationService authorizationService;
    
    @Override
    public TokenWrapperDto getToken(String authorization) {
        if (authorization != null && authorization.startsWith("Basic ")) {
            return authorizationService.verifyCredentials(authorization);
        }
        throw new MissingAuthorizationException("Nu sunteti autorizat sa accesati resursele");
    }
}
