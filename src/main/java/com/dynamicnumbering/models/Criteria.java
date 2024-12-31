package com.dynamicnumbering.models;

import com.dynamicnumbering.enums.CriteriaType;
import lombok.*;

@Data
@Builder
public class Criteria {

    private Integer id;

    private CriteriaType criteriaType;

    private int order;

    private String prefix;

    private String suffix;

    private Integer initialValue;

    private Integer maxLength;

    private Integer formatLength;
}
