package com.mbenz.file.storageservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Sushil.Kumar
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse implements Serializable {

    private Integer id;
    private String name;
    private String email;
    private Long salary;
    private Integer age;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dob;
}
