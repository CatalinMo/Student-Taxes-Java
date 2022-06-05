package com.student.taxes.application;

import com.student.taxes.domain.request.StudyFeeRequestDto;
import com.student.taxes.domain.StudyEntity;
import com.student.taxes.domain.StudyFeeEntity;
import com.student.taxes.domain.response.StudyFeeResponseDto;
import com.student.taxes.domain.response.StudyResponseDto;
import com.student.taxes.infrastructure.StudyFeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudyFeeService implements StudyFeeServiceApi {

    private final StudyFeeRepository studyFeeRepository;
    private final ModelMapper modelMapper;

    @Override
    public void updateStudy(Long id, StudyFeeRequestDto request) {
        StudyFeeEntity studyFeeEntity = studyFeeRepository.findById(id).orElseThrow(NullPointerException::new);
        modelMapper.map(request, studyFeeEntity);
        studyFeeRepository.save(studyFeeEntity);
    }

    @Override
    public void deleteStudyFee(Long id) {
        studyFeeRepository.deleteById(id);
    }

    @Override
    public List<StudyFeeResponseDto> getStudyFees() {
        List<StudyFeeEntity> studyFees = studyFeeRepository.findAll();
        return convertToStudyFeeDto(studyFees);
    }

    private List<StudyFeeResponseDto> convertToStudyFeeDto(List<StudyFeeEntity> studyFees) {
        return studyFees.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private StudyFeeResponseDto convert(StudyFeeEntity studyFeeEntity) {
        StudyFeeResponseDto studyFeeResponse = new StudyFeeResponseDto();
        studyFeeResponse.setId(studyFeeEntity.getId());
        studyFeeResponse.setName(studyFeeEntity.getName());
        studyFeeResponse.setValue(studyFeeEntity.getValue());
        studyFeeResponse.setType(studyFeeEntity.getType());
        studyFeeResponse.setStudy(convert(studyFeeEntity.getStudy()));
        return studyFeeResponse;
    }

    private StudyResponseDto convert(StudyEntity studyEntity) {
        StudyResponseDto studyResponse = new StudyResponseDto();
        studyResponse.setId(studyEntity.getId());
        studyResponse.setFaculty(studyEntity.getFaculty());
        studyResponse.setCycle(studyEntity.getCycle());
        studyResponse.setDepartment(studyEntity.getDepartment());
        studyResponse.setStudyProgram(studyEntity.getStudyProgram());
        studyResponse.setForm(studyEntity.getForm());
        studyResponse.setStudyFees(new ArrayList(studyEntity.getStudyFees()));
        studyResponse.setYear(studyEntity.getYear());
        studyResponse.setAbbreviation(studyEntity.getAbbreviation());
        return studyResponse;
    }
}
