package com.mbenz.storageservice.controller;

import com.mbenz.storageservice.dto.FileType;
import com.mbenz.storageservice.dto.HttpUserResponse;
import com.mbenz.storageservice.dto.UserRequest;
import com.mbenz.storageservice.dto.UserResponse;
import com.mbenz.storageservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * @author Sushil.Kumar
 */
@RestController
@RequestMapping("/v1/users")
@Validated
public class UserStorageController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserDetails(@PathVariable Integer id, @RequestParam(required = false) String name,
                                                      @RequestParam(required = false) String email) throws Exception {
        HttpUserResponse userResponse = userService.getUserDetails(id, name, email);
        return new ResponseEntity<>(userResponse.getUserResponse(), HttpStatus.OK);

        //return new ResponseEntity<>(new UserResponse("name", "email@email.com",new Long(3232),22,new Date()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveUserDetails(@Valid @RequestBody UserRequest userRequest, @RequestHeader FileType fileType){
        UserResponse userResponse = userService.saveUserDetails(userRequest, fileType);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserDetails(@Valid @RequestBody UserRequest userRequest, @PathVariable Integer id, @RequestHeader FileType fileType){
        UserResponse userResponse = userService.updateUserDetails(id, userRequest, fileType);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
}
