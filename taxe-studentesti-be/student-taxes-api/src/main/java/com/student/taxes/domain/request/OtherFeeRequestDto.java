package com.student.taxes.domain.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtherFeeRequestDto {

    private String name;
    private Float value;
    private String type;
}
