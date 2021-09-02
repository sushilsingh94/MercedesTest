package com.mbenz.storageservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author Sushil.Kumar
 */
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest implements Serializable {

    @NotBlank(message = "Name must not be empty")
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    @Min(value = 2, message = "Salary must be valid")
    private Long salary;
    @Min(value = 1, message = "Age must be valid")
    private Integer age;
    @NotBlank
    @Email(message = "Email must be valid email address")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRequest that = (UserRequest) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(dob, that.dob) &&
                Objects.equals(salary, that.salary) &&
                Objects.equals(age, that.age) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dob, salary, age, email);
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "name='" + name + '\'' +
                ", dob=" + dob +
                ", salary=" + salary +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
