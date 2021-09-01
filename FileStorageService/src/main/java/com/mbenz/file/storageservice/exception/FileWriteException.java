package com.mbenz.file.storageservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileWriteException extends RuntimeException{

    public FileWriteException(String exception, Object... values) {
        super(MessageFormat.format(exception, values));
    }

    public FileWriteException() {

    }
}
