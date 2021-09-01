package com.mbenz.storageservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbenz.storageservice.dto.*;
import com.mbenz.storageservice.exception.UserNotFoundException;
import com.mbenz.storageservice.kafka.KafKaProducerService;
import com.mbenz.storageservice.utils.AESUtil;
import com.mbenz.storageservice.utils.Constants;
import com.mbenz.storageservice.utils.HttpUtils;
import com.mbenz.storageservice.utils.JsonToStringConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;

/**
 * @author Sushil.Kumar
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Value("${filestorage.service.basepath}")
    private String servieBasepath;

    @Autowired
    private KafKaProducerService kafKaProducerService;

    @Autowired
    private JsonToStringConverter jsonToStringConverter;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public HttpUserResponse getUserDetails(Integer id, String name, String email) throws Exception {
        //call service 2 and get response
        //encode response and return to user.
        log.info("Method Entry");
        String url = servieBasepath.concat(Constants.API_GET_USER).replace("{id}", String.valueOf(id));
        HttpResponse<String> response = HttpUtils.makeGetCall(url);
        if(response == null || response.statusCode() != HttpStatus.OK.value()){
            throw new UserNotFoundException("Failed to get user details");
        }
        return new HttpUserResponse(response.statusCode(), objectMapper.readValue(AESUtil.decrypt(response.body()), UserResponse.class));
    }

    @Override
    public UserResponse saveUserDetails(UserRequest userRequest, FileType fileType) {
        log.info("Method Entry");
        UserResponse userResponse = new UserResponse(userRequest.getName(), userRequest.getEmail(), userRequest.getSalary(), userRequest.getAge(), userRequest.getDob());
        KafkaUserRequest kafkaUserRequest = new KafkaUserRequest(OperationType.CREATE, fileType, 0, userResponse);
        kafKaProducerService.sendMessage(AESUtil.encrypt(jsonToStringConverter.writeVauleAsString(kafkaUserRequest)));
        return userResponse;
    }

    @Override
    public UserResponse updateUserDetails(Integer id, UserRequest userRequest, FileType fileType) {
        log.info("Method Entry");
        UserResponse userResponse = new UserResponse(userRequest.getName(), userRequest.getEmail(), userRequest.getSalary(), userRequest.getAge(), userRequest.getDob());
        KafkaUserRequest kafkaUserRequest = new KafkaUserRequest(OperationType.UPDATE, fileType, id, userResponse);
        kafKaProducerService.sendMessage(AESUtil.encrypt(jsonToStringConverter.writeVauleAsString(kafkaUserRequest)));
        return userResponse;
    }
}
