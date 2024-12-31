package com.dynamicnumbering.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrantDTO {

    private String firstName;

    private String lastName;

    private String birthDate;
}