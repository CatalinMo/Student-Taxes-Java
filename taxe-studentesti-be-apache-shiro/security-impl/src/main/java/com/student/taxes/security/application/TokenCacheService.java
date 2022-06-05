package com.student.taxes.security.application;

import com.student.taxes.security.config.properties.JwtAuthProperties;
import com.student.taxes.security.domain.dto.AuthTokenDto;
import com.student.taxes.security.domain.dto.TokenDetailsDto;
import com.student.taxes.security.domain.dto.UserAuthentication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ConcurrentMap;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenCacheService {

    private final JwtAuthProperties jwtAuthProperties;
    private final ConcurrentMap<String, TokenDetailsDto> tokens;

    @Scheduled(fixedRate = 900000)
    public void cleanUp() {
        tokens.forEach((key, value) -> {
            Date currentDate = getCurrentDate();
            if (value.getValidUntil().before(currentDate)) {
                tokens.remove(key);
            }
        });
    }

    public void addToken(AuthTokenDto authToken) {
        Date validityDate = getValidityDate();
        TokenDetailsDto tokenDetails = new TokenDetailsDto(validityDate, authToken.getAuthentication());
        tokens.putIfAbsent(authToken.getToken(), tokenDetails);
    }

    public boolean isTokenValid(String token) {
        if (tokens.containsKey(token)) {
            return checkTokenValidity(token);
        }
        return false;
    }

    public UserAuthentication getAuthentication(String token) {
        return tokens.get(token).getAuthentication();
    }

    private boolean checkTokenValidity(String token) {
        TokenDetailsDto tokenDetailsDto = tokens.get(token);
        Date currentDate = getCurrentDate();
        if (tokenDetailsDto.getValidUntil().after(currentDate)) {
            tokenDetailsDto.setValidUntil(getValidityDate());
            tokens.replace(token, tokenDetailsDto);
            return true;
        } else {
            tokens.remove(token);
            return false;
        }
    }

    private Date getValidityDate() {
        long validUntil = System.currentTimeMillis() + jwtAuthProperties.getValidity();
        return new Date(validUntil);
    }

    private Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }
}
