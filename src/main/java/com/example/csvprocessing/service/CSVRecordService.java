package com.example.csvprocessing.service;

import com.example.csvprocessing.model.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

public interface CSVRecordService {

    void save(MultipartFile file);

    CSVRecord get(Long key);

    void delete(Long key);
}
