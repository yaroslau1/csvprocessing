package com.example.csvprocessing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CSVRecordDto {

    private Long key;
    private String name;
    private String description;
    private Long updated;
}
