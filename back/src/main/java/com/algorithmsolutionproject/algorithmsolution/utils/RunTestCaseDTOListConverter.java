package com.algorithmsolutionproject.algorithmsolution.utils;

import com.algorithmsolutionproject.algorithmsolution.dto.problem.RunTestCaseDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.List;

@Converter
public class RunTestCaseDTOListConverter implements AttributeConverter<List<RunTestCaseDTO>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<RunTestCaseDTO> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new RuntimeException("리스트 → JSON 직렬화 실패", e);
        }
    }

    @Override
    public List<RunTestCaseDTO> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<List<RunTestCaseDTO>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("JSON → 리스트 역직렬화 실패", e);
        }
    }
}
