package com.example.csvprocessing.validator;

import com.example.csvprocessing.constant.CSVHeaders;
import lombok.experimental.UtilityClass;
import org.apache.commons.csv.CSVRecord;

@UtilityClass
public class CSVRawDataValidator {

    public boolean isValid(CSVRecord csvRecord) {
        return !csvRecord.get(CSVHeaders.PRIMARY_KEY).equals("") &&
                !csvRecord.get(CSVHeaders.UPDATED_TIMESTAMP).equals("");
    }
}
