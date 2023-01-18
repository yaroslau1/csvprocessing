package com.example.csvprocessing.controller;

import com.example.csvprocessing.message.Response;
import com.example.csvprocessing.model.CSVRecord;
import com.example.csvprocessing.parser.CSVFileParser;
import com.example.csvprocessing.service.CSVRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CSVController {

    private final CSVRecordService recordService;

    private final CSVFileParser csvFileParser;

    @PostMapping("/csv/upload")
    public ResponseEntity<Response> uploadFile(@RequestBody MultipartFile file) {
        String message = "Please upload a csv file!";
        if (csvFileParser.hasCSVFormat(file)) {
            try {
                recordService.save(file);
                message = "File was uploaded successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new Response(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "! " + e.getMessage();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Response(message));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(message));
    }

    @GetMapping("/primary-key")
    public CSVRecord getByPrimaryKey(@RequestParam("primary-key") Long key) {
        return recordService.get(key);
    }

    @DeleteMapping("/primary-key")
    public void deleteByPrimaryKey(@RequestParam("primary-key") Long key) {
        recordService.delete(key);
    }
}
