package com.mbenz.storageservice.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbenz.storageservice.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JsonToStringConverter {

    @Autowired
    private ObjectMapper objectMapper;

    public String writeVauleAsString(Object userResponse){
        try {
            return objectMapper.writeValueAsString(userResponse);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
