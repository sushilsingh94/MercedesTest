package com.mbenz.file.storageservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaUserRequest implements Serializable {
    private OperationType operationType;
    private FileType fileType;
    private int id;
    private UserRequest request;
}
