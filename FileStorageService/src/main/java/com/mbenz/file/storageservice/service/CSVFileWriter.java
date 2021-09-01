package com.mbenz.file.storageservice.service;

import com.mbenz.file.storageservice.entity.Users;

import java.util.Optional;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.mbenz.file.storageservice.exception.FileWriteException;
import org.springframework.stereotype.Service;

@Service
public class CSVFileWriter implements FileWriter{
    @Override
    public Optional<String> writeToFile(Users users, String filePath) {
        filePath = filePath.concat(users.getClass().getSimpleName()).concat("_").concat(String.valueOf(users.getId())).concat("_").concat(users.getName()).concat(".csv");
        try {
            JsonNode jsonTree = new ObjectMapper().convertValue(users, JsonNode.class);
            CsvMapper csvMapper = new CsvMapper();
            CsvSchema csvSchema = csvMapper
                    .schemaFor(Users.class)
                    .withHeader();

            csvMapper.writerFor(JsonNode.class)
                    .with(csvSchema)
                    .writeValue(new File(filePath), jsonTree);
            return Optional.of(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileWriteException(e.getMessage());
        }
    }

}
