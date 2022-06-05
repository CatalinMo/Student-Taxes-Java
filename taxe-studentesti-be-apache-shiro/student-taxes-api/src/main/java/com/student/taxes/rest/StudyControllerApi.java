package com.student.taxes.rest;

import com.student.taxes.domain.request.StudyRequestDto;
import com.student.taxes.domain.response.StudyResponseDto;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiresAuthentication
@RequestMapping(path = "/student-taxes")
public interface StudyControllerApi {

    @PostMapping(path = "/create-study")
    void createStudy(@RequestBody StudyRequestDto request);

    @PutMapping(path = "/study/{id}")
    void updateStudy(@PathVariable Long id, @RequestBody StudyRequestDto request);

    @DeleteMapping(path = "/study/{id}")
    void deleteStudy(@PathVariable Long id);

    @DeleteMapping(path = "/active-study/{id}")
    void deleteActiveStudy(@PathVariable Long id);

    @GetMapping(path = "/studies")
    List<StudyResponseDto> getStudies();
}
