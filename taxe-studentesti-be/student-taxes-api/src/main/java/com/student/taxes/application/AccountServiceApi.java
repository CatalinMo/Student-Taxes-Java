package com.student.taxes.application;

import com.student.taxes.domain.request.AccountRequestDto;
import com.student.taxes.domain.request.ActiveFeeRequestDto;
import com.student.taxes.domain.request.AssignFeeToAccountRequestDto;
import com.student.taxes.domain.request.MarkFeeAsPaidRequestDto;
import com.student.taxes.domain.response.AccountResponseDto;
import com.student.taxes.domain.response.ActiveFeeResponseDto;
import com.student.taxes.domain.response.UserIdentityResponseDto;

import java.util.List;

public interface AccountServiceApi {

    void createAccount(AccountRequestDto request);

    void updateAccount(Long id, AccountRequestDto request);

    void updateActiveFee(Long id, ActiveFeeRequestDto request);

    void assignFeeToAccounts(AssignFeeToAccountRequestDto request);

    void markFeeAsPaid(MarkFeeAsPaidRequestDto request);

    void changePassword(Long id, String newPassword);

    void deleteAccount(Long id);

    void deleteAccountActiveFee(Long id);

    List<AccountResponseDto> getAccounts();

    AccountResponseDto getAccountByEmail(String email);

    AccountResponseDto getAccountByCnp(String cnp);

    List<ActiveFeeResponseDto> getActiveFees();

    UserIdentityResponseDto getUserIdentity(String user, String password);
}
