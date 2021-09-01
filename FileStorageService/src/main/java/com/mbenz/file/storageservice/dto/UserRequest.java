package com.mbenz.file.storageservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author Sushil.Kumar
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserRequest {

    private String name;
    private Date dob;
    private Long salary;
    private Integer age;
    private String email;

}
