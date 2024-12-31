package com.dynamicnumbering.models;

import lombok.*;
import java.time.LocalDate;

@Data
@Builder
public class Registrant {

    private Integer id;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;
}
