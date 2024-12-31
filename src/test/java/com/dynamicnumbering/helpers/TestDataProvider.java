package com.dynamicnumbering.helpers;

import com.dynamicnumbering.dtos.CriteriaDTO;
import com.dynamicnumbering.dtos.RegistrantDTO;
import com.dynamicnumbering.enums.CriteriaType;

import java.util.List;

public class TestDataProvider {

    // Test Case 1: Create criteria for the first case
    public static List<CriteriaDTO> createCriteriaForTest1() {
        return List.of(
                CriteriaDTO.builder()
                        .criteriaType(CriteriaType.FIRST_NAME)
                        .order(1)
                        .maxLength(3)
                        .suffix("-")
                        .build(),

                CriteriaDTO.builder()
                        .criteriaType(CriteriaType.LAST_NAME)
                        .order(2)
                        .maxLength(4)
                        .suffix("_")
                        .build(),

                CriteriaDTO.builder()
                        .criteriaType(CriteriaType.BIRTH_DATE)
                        .order(3)
                        .build(),

                CriteriaDTO.builder()
                        .criteriaType(CriteriaType.COUNTER)
                        .order(4)
                        .initialValue(10)
                        .prefix("C")
                        .formatLength(5)
                        .build()
        );
    }

    // Test Case 2: Create criteria for the second case
    public static List<CriteriaDTO> createCriteriaForTest2() {
        return List.of(
                CriteriaDTO.builder()
                        .criteriaType(CriteriaType.FIRST_NAME)
                        .order(2)
                        .maxLength(3)
                        .suffix("-")
                        .build(),

                CriteriaDTO.builder()
                        .criteriaType(CriteriaType.LAST_NAME)
                        .order(1)
                        .maxLength(4)
                        .suffix("_")
                        .build(),

                CriteriaDTO.builder()
                        .criteriaType(CriteriaType.BIRTH_DATE)
                        .order(4)
                        .prefix("N")
                        .build(),

                CriteriaDTO.builder()
                        .criteriaType(CriteriaType.COUNTER)
                        .order(3)
                        .initialValue(7)
                        .prefix("C")
                        .formatLength(5)
                        .build()
        );
    }

    // Create RegistrantDTO for Test Case 1
    public static RegistrantDTO createRegistrantForTest1() {
        return RegistrantDTO.builder()
                .firstName("Marc")
                .lastName("PASSAU")
                .birthDate("1974-04-24")
                .build();
    }

    // Create RegistrantDTO for Test Case 2
    public static RegistrantDTO createRegistrantForTest2() {
        return RegistrantDTO.builder()
                .firstName("Isaac")
                .lastName("ANTOINE")
                .birthDate("1992-04-24")
                .build();
    }
}
