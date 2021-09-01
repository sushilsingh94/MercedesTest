package com.mbenz.file.storageservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mbenz.file.storageservice.dto.FileType;
import com.mbenz.file.storageservice.dto.UserRequest;
import com.mbenz.file.storageservice.dto.UserResponse;

/**
 * @author Sushil.Kumar
 */
public interface UserService {

    String getUserDetails(Integer id, String name, String email) throws JsonProcessingException;

    UserResponse saveUserDetails(UserRequest userRequest, FileType fileType);

    UserResponse updateUserDetails(Integer id, UserRequest userRequest, FileType fileType) throws Exception;
}
