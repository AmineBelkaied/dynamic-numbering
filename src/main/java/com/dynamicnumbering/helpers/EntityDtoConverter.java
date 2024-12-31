package com.dynamicnumbering.helpers;

import com.dynamicnumbering.dtos.CriteriaDTO;
import com.dynamicnumbering.dtos.RegistrantDTO;
import com.dynamicnumbering.models.Criteria;
import com.dynamicnumbering.models.Registrant;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class EntityDtoConverter {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_DATE;

    public static CriteriaDTO toDto(Criteria criteria) {
        Objects.requireNonNull(criteria, "Criteria cannot be null");
        return CriteriaDTO.builder()
                .criteriaType(criteria.getCriteriaType())
                .prefix(criteria.getPrefix())
                .suffix(criteria.getSuffix())
                .maxLength(criteria.getMaxLength())
                .order(criteria.getOrder())
                .initialValue(criteria.getInitialValue())
                .formatLength(criteria.getFormatLength())
                .build();
    }

    public static Criteria toEntity(CriteriaDTO dto) {
        Objects.requireNonNull(dto, "CriteriaDTO cannot be null");
        return Criteria.builder()
                .criteriaType(dto.getCriteriaType())
                .prefix(dto.getPrefix())
                .suffix(dto.getSuffix())
                .maxLength(dto.getMaxLength())
                .order(dto.getOrder())
                .initialValue(dto.getInitialValue())
                .formatLength(dto.getFormatLength())
                .build();
    }

    public static RegistrantDTO toDto(Registrant registrant) {
        Objects.requireNonNull(registrant, "Registrant cannot be null");
        return RegistrantDTO.builder()
                .firstName(registrant.getFirstName())
                .lastName(registrant.getLastName())
                .birthDate(registrant.getBirthDate().format(DATE_FORMATTER))
                .build();
    }

    public static Registrant toEntity(RegistrantDTO dto) {
        Objects.requireNonNull(dto, "RegistrantDTO cannot be null");
        return Registrant.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .birthDate(LocalDate.parse(dto.getBirthDate(), DATE_FORMATTER))
                .build();
    }

    public static List<CriteriaDTO> toDtoList(List<Criteria> criteriaList) {
        Objects.requireNonNull(criteriaList, "Criteria list cannot be null");
        return criteriaList.stream()
                .map(EntityDtoConverter::toDto)
                .collect(Collectors.toList());
    }

    public static List<Criteria> toEntityList(List<CriteriaDTO> dtoList) {
        Objects.requireNonNull(dtoList, "CriteriaDTO list cannot be null");
        return dtoList.stream()
                .map(EntityDtoConverter::toEntity)
                .collect(Collectors.toList());
    }
}
