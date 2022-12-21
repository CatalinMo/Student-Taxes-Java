package com.student.taxes.rest;

import com.student.taxes.domain.request.AccountRequestDto;
import com.student.taxes.application.AccountServiceApi;
import com.student.taxes.domain.request.ActiveFeeRequestDto;
import com.student.taxes.domain.request.AssignFeeToAccountRequestDto;
import com.student.taxes.domain.request.MarkFeeAsPaidRequestDto;
import com.student.taxes.domain.response.AccountResponseDto;
import com.student.taxes.domain.response.ActiveFeeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountControllerImpl implements AccountControllerApi {

    private final AccountServiceApi accountService;

    @Override
    public void createStudy( AccountRequestDto request) {
        accountService.createAccount(request);
    }

    @Override
    public void updateAccount(Long id, AccountRequestDto request) {
        accountService.updateAccount(id, request);
    }

    @Override
    public void updateActiveFee(Long id, ActiveFeeRequestDto request) {
        accountService.updateActiveFee(id, request);
    }

    @Override
    public void assignFeeToAccounts(AssignFeeToAccountRequestDto request) {
        accountService.assignFeeToAccounts(request);
    }

    @Override
    public void markFeeAsPaid(MarkFeeAsPaidRequestDto request) {
        accountService.markFeeAsPaid(request);
    }

    @Override
    public void changePassword(Long id, String newPassword) {
        accountService.changePassword(id, newPassword);
    }

    @Override
    public void deleteAccount(Long id) {
        accountService.deleteAccount(id);
    }

    @Override
    public void deleteAccountActiveFee(Long id) {
        accountService.deleteAccountActiveFee(id);
    }

    @Override
    public List<AccountResponseDto> getAccounts() {
        return accountService.getAccounts();
    }

    @Override
    public AccountResponseDto getAccountByEmail(String email) {
        return accountService.getAccountByEmail(email);
    }

    @Override
    public AccountResponseDto getAccountByCnp(String cnp) {
        return accountService.getAccountByCnp(cnp);
    }

    @Override
    public List<ActiveFeeResponseDto> getActiveFees() {
        return accountService.getActiveFees();
    }
}
