package com.dynamicnumbering.services.impl;

import ch.qos.logback.core.util.StringUtil;
import com.dynamicnumbering.dtos.CriteriaDTO;
import com.dynamicnumbering.dtos.RegistrantDTO;
import com.dynamicnumbering.models.Criteria;
import com.dynamicnumbering.models.Registrant;
import com.dynamicnumbering.helpers.EntityDtoConverter;
import com.dynamicnumbering.services.NumberingService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class NumberingServiceImpl implements NumberingService {

    // In-memory storage for configured criteria per user or identifier
    private final Map<String, List<Criteria>> configuredCriteriaMap = new ConcurrentHashMap<>();

    // Store criteria based on identifier (e.g., userId, categoryId, etc.)
    public void configureCriteria(String identifier, List<CriteriaDTO> criteriaDTOList) {
        Objects.requireNonNull(identifier, "Identifier for criteria configuration cannot be null");
        Objects.requireNonNull(criteriaDTOList, "Criteria list cannot be null");

        List<Criteria> criteriaList = EntityDtoConverter.toEntityList(criteriaDTOList);
        configuredCriteriaMap.put(identifier, criteriaList);
    }

    @Override
    public List<String> getIdentifiers() {
        return new ArrayList<>(configuredCriteriaMap.keySet());
    }

    public String generateNumber(String identifier, RegistrantDTO registrantDTO) {
        if (identifier == null) {
            throw new IllegalArgumentException("Identifier cannot be null");
        }
        if (registrantDTO == null) {
            throw new IllegalArgumentException("RegistrantDTO cannot be null");
        }

        Registrant registrant = EntityDtoConverter.toEntity(registrantDTO);
        List<Criteria> criteriaList = configuredCriteriaMap.get(identifier);

        if (criteriaList == null || criteriaList.isEmpty()) {
            throw new IllegalArgumentException("No criteria configured for identifier: " + identifier);
        }

        StringBuilder generatedNumber = new StringBuilder();

        criteriaList.stream()
                .sorted(Comparator.comparingInt(Criteria::getOrder))
                .forEach(criteria -> {
                    switch (criteria.getCriteriaType()) {
                        case FIRST_NAME -> buildFirstOrLastName(registrant.getFirstName(), criteria, generatedNumber);
                        case LAST_NAME -> buildFirstOrLastName(registrant.getLastName(), criteria, generatedNumber);
                        case BIRTH_DATE -> buildBirthDate(registrant.getBirthDate(), criteria, generatedNumber);
                        case COUNTER -> buildCounter(criteria, generatedNumber);
                        default -> throw new IllegalStateException("Unknown criteria type: " + criteria.getCriteriaType());
                    }
                });

        return generatedNumber.toString();
    }


    private void buildFirstOrLastName(String name, Criteria criteria, StringBuilder generatedNumber) {
        if (name == null) {
            throw new IllegalArgumentException("Registrant name cannot be null for criteria type: " + criteria.getCriteriaType());
        }
        if (StringUtil.notNullNorEmpty(name)) {
            String subName = "";
            if (criteria.getMaxLength() > 0) {
                subName += name.substring(0, Math.min(name.length(), criteria.getMaxLength())).toUpperCase();
            }
            appendIfNotNullOrEmpty(criteria.getPrefix(), generatedNumber);

            generatedNumber.append(subName);

            appendIfNotNullOrEmpty(criteria.getSuffix(), generatedNumber);
        }
    }

    private void buildBirthDate(LocalDate birthDate, Criteria criteria, StringBuilder generatedNumber) {
        if (birthDate == null) {
            throw new IllegalArgumentException("Birth date cannot be null for criteria type: " + criteria.getCriteriaType());
        }
        appendIfNotNullOrEmpty(criteria.getPrefix(), generatedNumber);

        generatedNumber.append(birthDate.getYear());

        appendIfNotNullOrEmpty(criteria.getSuffix(), generatedNumber);
    }

    private void buildCounter(Criteria criteria, StringBuilder generatedNumber) {
        appendIfNotNullOrEmpty(criteria.getPrefix(), generatedNumber);

        Integer counter = criteria.getInitialValue() + 1;
        criteria.setInitialValue(counter);
        String formattedCounter = String.format("%0" + criteria.getFormatLength() + "d", counter);
        generatedNumber.append(formattedCounter);

        appendIfNotNullOrEmpty(criteria.getSuffix(), generatedNumber);
    }

    private void appendIfNotNullOrEmpty(String criteria, StringBuilder generatedNumber) {
        if (StringUtil.notNullNorEmpty(criteria)) {
            generatedNumber.append(criteria);
        }
    }
}