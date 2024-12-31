package com.dynamicnumbering.dtos;

import com.dynamicnumbering.enums.CriteriaType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CriteriaDTO {

    private CriteriaType criteriaType;

    private int order;

    private String prefix;

    private String suffix;

    private Integer maxLength;

    private Integer initialValue;

    private Integer formatLength;
}