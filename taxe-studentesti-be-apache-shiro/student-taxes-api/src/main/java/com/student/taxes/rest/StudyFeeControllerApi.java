package com.student.taxes.rest;

import com.student.taxes.domain.request.StudyFeeRequestDto;
import com.student.taxes.domain.response.StudyFeeResponseDto;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiresAuthentication
@RequestMapping(path = "/student-taxes")
public interface StudyFeeControllerApi {

    @PutMapping(path = "/study-fee/{id}")
    void updateStudyFee(@PathVariable Long id, @RequestBody StudyFeeRequestDto request);

    @DeleteMapping(path = "/study-fee/{id}")
    void deleteStudyFee(@PathVariable Long id);

    @GetMapping(path = "/study-fees")
    List<StudyFeeResponseDto> getStudyFees();
}
