package com.dynamicnumbering.controllers;

import com.dynamicnumbering.dtos.CriteriaDTO;
import com.dynamicnumbering.dtos.RegistrantDTO;
import com.dynamicnumbering.helpers.TestDataProvider;
import com.dynamicnumbering.services.NumberingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class NumberingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private NumberingService numberingService;

    @InjectMocks
    private NumberingController numberingController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(numberingController).build();
    }

    // Test case for configuring criteria from TestDataProvider for Scenario 1
    @Test
    public void testConfigureCriteriaScenario1() throws Exception {
        List<CriteriaDTO> criteriaDTOList = TestDataProvider.createCriteriaForTest1();
        String identifier = "testIdentifier";

        mockMvc.perform(post("/api/numbering/configure/{identifier}", identifier)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(criteriaDTOList)))
                .andExpect(status().isOk())
                .andExpect(content().string("Criteria configured successfully!"));

        // Verify that the service method is called with the correct parameters
        verify(numberingService, times(1)).configureCriteria(eq(identifier), eq(criteriaDTOList));
    }

    // Test case for configuring criteria from TestDataProvider for Scenario 2
    @Test
    public void testConfigureCriteriaScenario2() throws Exception {
        List<CriteriaDTO> criteriaDTOList = TestDataProvider.createCriteriaForTest2();
        String identifier = "testIdentifier";

        mockMvc.perform(post("/api/numbering/configure/{identifier}", identifier)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(criteriaDTOList)))
                .andExpect(status().isOk())
                .andExpect(content().string("Criteria configured successfully!"));

        // Verify that the service method is called with the correct parameters
        verify(numberingService, times(1)).configureCriteria(eq(identifier), eq(criteriaDTOList));
    }

    // Test case for generating a number using the registrant and criteria from Scenario 1
    @Test
    public void testGenerateNumberScenario1() throws Exception {
        RegistrantDTO registrantDTO = TestDataProvider.createRegistrantForTest1();
        String identifier = "identifier";

        // Setup mock response for generateNumber
        when(numberingService.generateNumber(identifier, registrantDTO))
                .thenReturn("MAR-PASS_1974C00011");

        mockMvc.perform(post("/api/numbering/generate/{identifier}", identifier)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(registrantDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("MAR-PASS_1974C00011"));

        // Verify that the service method is called
        verify(numberingService, times(1)).generateNumber(eq(identifier), eq(registrantDTO));
    }

    // Test case for generating a number using the registrant and criteria from Scenario 2
    @Test
    public void testGenerateNumberScenario2() throws Exception {
        RegistrantDTO registrantDTO = TestDataProvider.createRegistrantForTest2();
        String identifier = "testIdentifier";

        // Setup mock response for generateNumber
        when(numberingService.generateNumber(identifier, registrantDTO))
                .thenReturn("ANTO_ISA-C00008N1992");

        mockMvc.perform(post("/api/numbering/generate/{identifier}", identifier)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(registrantDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("ANTO_ISA-C00008N1992"));

        // Verify that the service method is called
        verify(numberingService, times(1)).generateNumber(eq(identifier), eq(registrantDTO));
    }
}
