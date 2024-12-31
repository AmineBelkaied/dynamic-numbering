package com.dynamicnumbering.controllers;

import com.dynamicnumbering.dtos.RegistrantDTO;
import com.dynamicnumbering.dtos.CriteriaDTO;
import com.dynamicnumbering.services.NumberingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/numbering")
public class NumberingController {

    private final NumberingService numberingService;

    public NumberingController(NumberingService numberingService) {
        this.numberingService = numberingService;
    }

    @PostMapping("/configure/{identifier}")
    public String configureCriteria(@PathVariable String identifier, @RequestBody List<CriteriaDTO> criteriaDTOList) {
        numberingService.configureCriteria(identifier, criteriaDTOList);
        return "Criteria configured successfully!";
    }

    @PostMapping("/generate/{identifier}")
    public String generateNumber(@PathVariable String identifier, @RequestBody RegistrantDTO registrantDTO) {
        return numberingService.generateNumber(identifier, registrantDTO);
    }

    @GetMapping("/identifiers")
    public List<String> getIdentifiers() {
        return numberingService.getIdentifiers();
    }
}
