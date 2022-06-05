package com.student.taxes.security.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class TokenDetailsDto {

    private Date validUntil;
    private UserAuthentication authentication;
}
