package com.dynamicnumbering.services;

import com.dynamicnumbering.dtos.CriteriaDTO;
import com.dynamicnumbering.dtos.RegistrantDTO;
import com.dynamicnumbering.helpers.TestDataProvider;
import com.dynamicnumbering.services.impl.NumberingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberingServiceTest {

    private NumberingServiceImpl numberingService;

    @BeforeEach
    public void setUp() {
        numberingService = new NumberingServiceImpl();
    }

    // Test case for generating number based on Test Case 1 criteria
    @Test
    public void testGenerateNumber_case1() {
        // Setup criteria and registrant information
        List<CriteriaDTO> criteriaDTOList = TestDataProvider.createCriteriaForTest1();
        String identifier = "criteria1";
        numberingService.configureCriteria(identifier, criteriaDTOList);

        RegistrantDTO registrantDTO = TestDataProvider.createRegistrantForTest1();

        // Generate the number based on the provided information
        String generatedNumber = numberingService.generateNumber(identifier, registrantDTO);

        // Check if the generated number matches the expected format
        String expectedNumber = "MAR-PASS_1974C00011";
        assertEquals(expectedNumber, generatedNumber, "The generated number should match the expected format.");
    }

    // Test case for generating number based on Test Case 2 criteria
    @Test
    public void testGenerateNumber_case2() {
        // Setup criteria and registrant information
        List<CriteriaDTO> criteriaDTOList = TestDataProvider.createCriteriaForTest2();
        String identifier = "criteria2";
        numberingService.configureCriteria(identifier, criteriaDTOList);

        RegistrantDTO registrantDTO = TestDataProvider.createRegistrantForTest2();

        // Generate the number based on the provided information
        String generatedNumber = numberingService.generateNumber(identifier, registrantDTO);

        // Check if the generated number matches the expected format
        String expectedNumber = "ANTO_ISA-C00008N1992";
        assertEquals(expectedNumber, generatedNumber, "The generated number should match the expected format.");
    }
}
