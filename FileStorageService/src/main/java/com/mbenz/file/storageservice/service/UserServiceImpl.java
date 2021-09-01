package com.mbenz.file.storageservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbenz.file.storageservice.dto.FileType;
import com.mbenz.file.storageservice.dto.UserRequest;
import com.mbenz.file.storageservice.dto.UserResponse;
import com.mbenz.file.storageservice.entity.Users;
import com.mbenz.file.storageservice.exception.UserNotFoundException;
import com.mbenz.file.storageservice.repository.UserRepository;
import com.mbenz.file.storageservice.utils.AESUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Sushil.Kumar
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Value("${user.storage.file.path}")
    private String fileStoragePath;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileWriterFactory fileWriterFactory;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String getUserDetails(Integer id, String name, String email) throws JsonProcessingException {
        Users user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        String response = AESUtil.encrypt(objectMapper.writeValueAsString(user.toUserResponse()));
        return response;
    }

    @Override
    public UserResponse saveUserDetails(UserRequest userRequest, FileType fileType) {
        Users user = new Users(userRequest.getName(), userRequest.getEmail(), userRequest.getSalary(), userRequest.getAge(), userRequest.getDob());
        Optional<String> filePath = fileWriterFactory.getInstance(fileType).writeToFile(user, fileStoragePath);
        user.setFilePath(filePath.orElse(""));
        userRepository.save(user);
        log.info("User details saved to DB and written to file at {}", filePath.get());
        return user.toUserResponse();
    }

    @Override
    public UserResponse updateUserDetails(Integer id, UserRequest userRequest, FileType fileType){
        Optional<Users> userOptional = userRepository.findById(id);
        Users user = userOptional.orElseThrow(UserNotFoundException::new);
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setAge(userRequest.getAge());
        user.setSalary(userRequest.getSalary());
        user.setDob(userRequest.getDob());
        Optional<String> filePath = fileWriterFactory.getInstance(fileType).writeToFile(user, fileStoragePath);
        user.setFilePath(filePath.orElse(""));
        userRepository.save(user);
        log.info("User details updated to DB and written to file at {}", filePath.get());
        return user.toUserResponse();
    }
}
