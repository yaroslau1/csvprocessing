package com.example.csvprocessing.parser;

import com.example.csvprocessing.constant.CSVHeaders;
import com.example.csvprocessing.constant.FileConstants;
import com.example.csvprocessing.dto.CSVRecordDto;
import com.example.csvprocessing.validator.CSVRawDataValidator;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVFileParser implements Parser {

    public boolean hasCSVFormat(MultipartFile file) {
        return FileConstants.CSV_FILE_TYPE.equals(file.getContentType());
    }

    public List<CSVRecordDto> parse(InputStream is) {
        checkEmptyLastLine(is);
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<CSVRecordDto> csvRecordDtos = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                if (CSVRawDataValidator.isValid(csvRecord)) {
                    CSVRecordDto csvRecordDto = CSVRecordDto.builder()
                            .key(Long.parseLong(csvRecord.get(CSVHeaders.PRIMARY_KEY)))
                            .name(csvRecord.get(CSVHeaders.NAME))
                            .description(csvRecord.get(CSVHeaders.DESCRIPTION))
                            .updated(Long.parseLong(csvRecord.get(CSVHeaders.UPDATED_TIMESTAMP)))
                            .build();
                    csvRecordDtos.add(csvRecordDto);
                }
            }
            return csvRecordDtos;
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse CSV file: " + e.getMessage());
        }
    }

    private void checkEmptyLastLine(InputStream is) {
        try {
            while (is.available() > FileConstants.NO_AVAILABLE_SYMBOL) {
                if (is.available() == FileConstants.LAST_AVAILABLE_SYMBOL) {
                    int lastSymbol = is.read();
                    if(lastSymbol != FileConstants.EMPTY_LINE_SYMBOL){
                        throw new RuntimeException("Last line is not empty");
                    }
                    System.out.println(lastSymbol);
                }
                is.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse CSV file: " + e.getMessage());
        }
    }
}
