package com.example.csvprocessing.parser;

import com.example.csvprocessing.dto.CSVRecordDto;

import java.io.InputStream;
import java.util.List;

public interface Parser {
    List<CSVRecordDto> parse(InputStream is);
}
