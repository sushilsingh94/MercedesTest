package com.mbenz.file.storageservice.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class Utils {

    public static boolean checkPathExists(String outputPath){
        if(Files.notExists(Path.of(outputPath))){
            try {
                Files.createDirectories(Path.of(outputPath));
                log.info("Output Path created {}", outputPath);
                return true;
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        return false;
    }
}
