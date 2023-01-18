package com.example.csvprocessing.converter;

import com.example.csvprocessing.dto.CSVRecordDto;
import com.example.csvprocessing.model.CSVRecord;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CSVRecordConverter {

    public CSVRecord toEntity(CSVRecordDto dto) {
        CSVRecord entity = new CSVRecord();
        entity.setKey(dto.getKey());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setUpdated(TimestampConverter.toLocalDateTime(dto.getUpdated()));
        return entity;
    }

    public CSVRecordDto toDto(CSVRecord entity) {
        CSVRecordDto dto = new CSVRecordDto();
        dto.setKey(entity.getKey());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setUpdated(TimestampConverter.toTimestamp(entity.getUpdated()));
        return dto;
    }
}
