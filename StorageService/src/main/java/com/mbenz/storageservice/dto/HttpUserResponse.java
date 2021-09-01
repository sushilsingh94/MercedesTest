package com.mbenz.storageservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HttpUserResponse {
    private int statusCode;
    private UserResponse userResponse;

}
