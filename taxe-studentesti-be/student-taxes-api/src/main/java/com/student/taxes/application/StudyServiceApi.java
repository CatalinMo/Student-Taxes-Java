package com.student.taxes.application;

import com.student.taxes.domain.request.StudyRequestDto;
import com.student.taxes.domain.response.StudyResponseDto;

import java.util.List;

public interface StudyServiceApi {

    void createStudy(StudyRequestDto request);

    void updateStudy(Long id, StudyRequestDto request);

    void deleteStudy(Long id);

    void deleteActiveStudy(Long id);

    List<StudyResponseDto> getStudies();
}
