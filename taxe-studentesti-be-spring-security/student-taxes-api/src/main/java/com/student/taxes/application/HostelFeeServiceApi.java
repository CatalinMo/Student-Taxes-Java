package com.student.taxes.application;

import com.student.taxes.domain.request.HostelFeeRequestDto;
import com.student.taxes.domain.response.HostelFeeResponseDto;

import java.util.List;

public interface HostelFeeServiceApi {

    void createHostelFee(HostelFeeRequestDto request);

    void updateHostelFee(Long id, HostelFeeRequestDto request);

    void deleteHostelFee(Long id);

    List<HostelFeeResponseDto> getHostelFees();
}
