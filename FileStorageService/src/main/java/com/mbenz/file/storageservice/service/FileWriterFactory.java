package com.mbenz.file.storageservice.service;

import com.mbenz.file.storageservice.dto.FileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileWriterFactory {

    @Autowired
    private XMLFileWriter xmlFileWriter;

    @Autowired
    private CSVFileWriter csvFileWriter;

    public FileWriter getInstance(FileType fileType){
        if(FileType.CSV.name().equals(fileType.name())){
            return csvFileWriter;
        }else{
            return xmlFileWriter;
        }
    }
}
