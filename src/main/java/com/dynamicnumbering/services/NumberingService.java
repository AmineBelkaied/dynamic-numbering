package com.dynamicnumbering.services;

import com.dynamicnumbering.dtos.CriteriaDTO;
import com.dynamicnumbering.dtos.RegistrantDTO;

import java.util.List;

public interface NumberingService {

    void configureCriteria(String identifier, List<CriteriaDTO> criteriaDTOList);
    List<String> getIdentifiers();
    String generateNumber(String identifier, RegistrantDTO registrantDTO);
}
