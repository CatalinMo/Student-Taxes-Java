package com.student.taxes.rest;

import com.student.taxes.domain.request.OtherFeeRequestDto;
import com.student.taxes.domain.response.OtherFeeResponseDto;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiresAuthentication
@RequestMapping(path = "/student-taxes")
public interface OtherFeeControllerApi {

    @PostMapping(path = "/create-other-fee")
    void createOtherFee(@RequestBody OtherFeeRequestDto request);

    @PutMapping(path = "/other-fee/{id}")
    void updateOtherFee(@PathVariable Long id, @RequestBody OtherFeeRequestDto request);

    @DeleteMapping(path = "/other-fee/{id}")
    void deleteOtherFee(@PathVariable Long id);

    @GetMapping(path = "/other-fees")
    List<OtherFeeResponseDto> getOtherFees();
}
