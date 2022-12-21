package com.student.taxes.rest;

import com.student.taxes.domain.request.OtherFeeRequestDto;
import com.student.taxes.application.OtherFeeServiceApi;
import com.student.taxes.domain.response.OtherFeeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OtherFeeControllerImpl implements OtherFeeControllerApi {

    private final OtherFeeServiceApi otherFeeService;

    @Override
    public void createOtherFee(OtherFeeRequestDto request) {
        otherFeeService.createOtherFee(request);
    }

    @Override
    public void updateOtherFee(Long id, OtherFeeRequestDto request) {
        otherFeeService.updateOtherFee(id, request);
    }

    @Override
    public void deleteOtherFee(Long id) {
        otherFeeService.deleteOtherFee(id);
    }

    @Override
    public List<OtherFeeResponseDto> getOtherFees() {
        return otherFeeService.getOtherFees();
    }
}
