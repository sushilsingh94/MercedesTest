package com.mbenz.file.storageservice.service;

import com.mbenz.file.storageservice.entity.Users;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class CSVFileWriterTest {

    @InjectMocks
    CSVFileWriter csvFileWriter;

    @After
    public void cleanup() {
        new File("src/test/resources/Users_null_name.csv").deleteOnExit();
    }

    @Test
    public void writeToFile_Test(){
        Users user = new Users("name", "email@email.com", new Long(23232), 3232, new Date());
        csvFileWriter.writeToFile(user, "src/test/resources/");
    }
}
