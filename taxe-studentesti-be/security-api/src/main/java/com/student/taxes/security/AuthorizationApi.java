package com.student.taxes.security;

import com.student.taxes.security.domain.dto.TokenWrapperDto;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface AuthorizationApi {

    @GetMapping(value = "/auth/token")
    TokenWrapperDto getToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization);
}
