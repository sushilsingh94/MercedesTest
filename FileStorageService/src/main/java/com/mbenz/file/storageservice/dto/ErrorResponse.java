package com.mbenz.file.storageservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Sushil.Kumar
 */
@Data
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private List<String> details;

}