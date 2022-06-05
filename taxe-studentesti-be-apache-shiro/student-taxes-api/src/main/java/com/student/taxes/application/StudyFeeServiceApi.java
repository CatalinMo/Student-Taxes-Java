package com.student.taxes.application;

import com.student.taxes.domain.request.StudyFeeRequestDto;
import com.student.taxes.domain.response.StudyFeeResponseDto;

import java.util.List;

public interface StudyFeeServiceApi {

    void updateStudy(Long id, StudyFeeRequestDto request);

    void deleteStudyFee(Long id);

    List<StudyFeeResponseDto> getStudyFees();
}
