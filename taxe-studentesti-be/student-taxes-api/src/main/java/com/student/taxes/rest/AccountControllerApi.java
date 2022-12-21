package com.student.taxes.rest;

import com.student.taxes.domain.request.AccountRequestDto;
import com.student.taxes.domain.request.ActiveFeeRequestDto;
import com.student.taxes.domain.request.AssignFeeToAccountRequestDto;
import com.student.taxes.domain.request.MarkFeeAsPaidRequestDto;
import com.student.taxes.domain.response.AccountResponseDto;
import com.student.taxes.domain.response.ActiveFeeResponseDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/student-taxes")
public interface AccountControllerApi {

    @PostMapping(path = "/create-account")
    void createStudy(@RequestBody AccountRequestDto request);

    @PutMapping(path = "/account/{id}")
    void updateAccount(@PathVariable Long id, @RequestBody AccountRequestDto request);

    @PutMapping(path = "/account/active-fee/{id}")
    void updateActiveFee(@PathVariable Long id, @RequestBody ActiveFeeRequestDto request);

    @PutMapping(path = "/accounts")
    void assignFeeToAccounts(@RequestBody AssignFeeToAccountRequestDto request);

    @PutMapping(path = "/account/mark-fee")
    void markFeeAsPaid(@RequestBody MarkFeeAsPaidRequestDto request);

    @PutMapping(path = "/account/{id}/change-password")
    void changePassword(@PathVariable Long id, @RequestBody String newPassword);

    @DeleteMapping(path = "/account/{id}")
    void deleteAccount(@PathVariable Long id);

    @DeleteMapping(path = "/account/active-fee/{id}")
    void deleteAccountActiveFee(@PathVariable Long id);

    @GetMapping(path = "/accounts")
    @PreAuthorize("hasAnyAuthority('Admin', 'Birou Taxe')")
    List<AccountResponseDto> getAccounts();

    @GetMapping(path = "/account-email/{email}/")
    AccountResponseDto getAccountByEmail(@PathVariable String email);

    @GetMapping(path = "/account-cnp/{cnp}")
    AccountResponseDto getAccountByCnp(@PathVariable String cnp);

    @GetMapping(path = "/accounts/active-fees")
    List<ActiveFeeResponseDto> getActiveFees();
}
