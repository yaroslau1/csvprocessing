package com.example.csvprocessing.repository;

import com.example.csvprocessing.model.CSVRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CSVRecordRepository extends JpaRepository<CSVRecord, Long> {
}
