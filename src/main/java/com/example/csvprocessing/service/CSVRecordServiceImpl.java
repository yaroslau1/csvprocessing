package com.example.csvprocessing.service;

import com.example.csvprocessing.converter.CSVRecordConverter;
import com.example.csvprocessing.dto.CSVRecordDto;
import com.example.csvprocessing.model.CSVRecord;
import com.example.csvprocessing.parser.Parser;
import com.example.csvprocessing.repository.CSVRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CSVRecordServiceImpl implements CSVRecordService {

    private final CSVRecordRepository csvRecordRepository;

    private final Parser fileParser;

    @Override
    @Transactional
    public void save(MultipartFile file) {
        try {
            List<CSVRecordDto> csvRecordDtos = fileParser.parse(file.getInputStream());
            List<CSVRecord> csvRecords = getEntities(csvRecordDtos);
            csvRecordRepository.saveAll(csvRecords);
            log.info("Saved " + csvRecords.size() + " records.");
        } catch (IOException e) {
            throw new RuntimeException("Fail to store csv data: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public CSVRecord get(Long key) {
        Optional<CSVRecord> entity = csvRecordRepository.findById(key);
        if (entity.isPresent()) {
            log.info("Entity found : " + entity.get());
            return entity.get();
        }
        throw new RuntimeException("Record with key:" + key + " not found.");
    }

    @Override
    @Transactional
    public void delete(Long key) {
        CSVRecord csvRecord = get(key);
        csvRecordRepository.deleteById(key);
        log.info("Entity deleted : " + csvRecord);
    }

    private List<CSVRecord> getEntities(List<CSVRecordDto> csvRecordDtos) {
        return csvRecordDtos.stream()
                .map(CSVRecordConverter::toEntity)
                .collect(Collectors.toList());
    }
}
