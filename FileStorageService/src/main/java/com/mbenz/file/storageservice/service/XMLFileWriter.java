package com.mbenz.file.storageservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mbenz.file.storageservice.entity.Users;
import com.mbenz.file.storageservice.exception.FileWriteException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class XMLFileWriter implements FileWriter{
    @Override
    public Optional<String> writeToFile(Users users, String filePath) {
        filePath = filePath.concat(users.getClass().getSimpleName()).concat("_").concat(String.valueOf(users.getId())).concat("_").concat(users.getName()).concat(".xml");
        try {
            ObjectMapper xmlMapper = new XmlMapper();
            xmlMapper.writeValue(new File(filePath), users);
            return Optional.of(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileWriteException(e.getMessage());
        }
    }

}
