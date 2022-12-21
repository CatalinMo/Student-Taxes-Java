package com.student.taxes.security.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class AuthTokenDto {

    private String token;
    private UserAuthentication authentication;
}
