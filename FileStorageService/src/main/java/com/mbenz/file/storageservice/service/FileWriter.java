package com.mbenz.file.storageservice.service;

import com.mbenz.file.storageservice.entity.Users;

import java.util.Optional;

public interface FileWriter {

    Optional<String> writeToFile(Users users, String filePath);
}
