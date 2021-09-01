package com.mbenz.file.storageservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mbenz.file.storageservice.dto.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@JsonPropertyOrder({
        "name",
        "email",
        "salary",
        "age",
        "dob"
})
public class Users extends AuditableEntity{
    @Id
    @GeneratedValue
    @Column
    @JsonProperty
    private Integer id;
    @Column(length = 255)
    @JsonProperty
    private String name;
    @Column(length = 255)
    @JsonProperty
    private String email;
    @Column(length = 10)
    @JsonProperty
    private Long salary;
    @Column(length = 3)
    @JsonProperty
    private Integer age;
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty
    private Date dob;

    @Column
    @JsonIgnore
    private String filePath;

    public Users(String name, String email, Long salary, Integer age, Date dob){
        this.name =  name;
        this.email = email;
        this.salary = salary;
        this.age = age;
        this.dob = dob;
        this.setCreatedBy("Default");
        this.setCreatedOn(new Timestamp(System.currentTimeMillis()));
    }

    public UserResponse toUserResponse(){
        return new UserResponse(this.id, this.name, this.email, this.salary, this.age, this.dob);
    }
}
