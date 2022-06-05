package com.student.taxes.rest;

import com.student.taxes.domain.request.HostelFeeRequestDto;
import com.student.taxes.domain.response.HostelFeeResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/student-taxes")
public interface HostelFeeControllerApi {

    @PostMapping(path = "/create-hostel-fee")
    void createHostelFee(@RequestBody HostelFeeRequestDto request);

    @PutMapping(path = "/hostel-fee/{id}")
    void updateHostelFee(@PathVariable Long id, @RequestBody HostelFeeRequestDto request);

    @DeleteMapping(path = "/hostel-fee/{id}")
    void deleteHostelFee(@PathVariable Long id);

    @GetMapping(path = "/hostel-fees")
    List<HostelFeeResponseDto> getHostelFees();
}
