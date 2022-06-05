package com.student.taxes.security.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class TokenDetailsDto {

    private Date validUntil;
    private Authentication authentication;
}
