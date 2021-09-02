package com.mbenz.storageservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mbenz.storageservice.dto.FileType;
import com.mbenz.storageservice.dto.HttpUserResponse;
import com.mbenz.storageservice.dto.UserRequest;
import com.mbenz.storageservice.dto.UserResponse;

/**
 * @author Sushil.Kumar
 */
public interface UserService {

    HttpUserResponse getUserDetails(Integer id, String name, String email) throws Exception;

    UserResponse saveUserDetails(UserRequest userRequest, FileType fileType) throws JsonProcessingException;

    UserResponse updateUserDetails(Integer id, UserRequest userRequest, FileType fileType) throws JsonProcessingException;
}
