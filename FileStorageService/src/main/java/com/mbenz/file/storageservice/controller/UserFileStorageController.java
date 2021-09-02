package com.mbenz.file.storageservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mbenz.file.storageservice.dto.FileType;
import com.mbenz.file.storageservice.dto.UserRequest;
import com.mbenz.file.storageservice.dto.UserResponse;
import com.mbenz.file.storageservice.service.UserService;
import com.mbenz.file.storageservice.utils.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Sushil.Kumar
 */
@RestController
@RequestMapping("/v1/users")
@Validated
public class UserFileStorageController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserDetails(@PathVariable Integer id, @RequestParam(required = false) String name,
                                                       @RequestParam(required = false) String email) throws JsonProcessingException {
        String userResponse = userService.getUserDetails(id, name, email);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
        //return new ResponseEntity<>(AESUtil.encrypt("{\"id\":2,\"name\":\"Sushilvm\",\"email\":\"sushil@gmail.com\",\"salary\":234,\"age\":23,\"dob\":\"2021-08-29\"}"), HttpStatus.OK);
    }

    /*@PostMapping
    public ResponseEntity<?> saveUserDetails(@Valid @RequestBody UserRequest userRequest, @RequestHeader FileType fileType){
        UserResponse userResponse = userService.saveUserDetails(userRequest, fileType);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserDetails(@Valid @RequestBody UserRequest userRequest, @PathVariable Integer id,
                                               @RequestHeader FileType fileType) throws Exception {
        UserResponse userResponse = userService.updateUserDetails(id, userRequest, fileType);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }*/
}
