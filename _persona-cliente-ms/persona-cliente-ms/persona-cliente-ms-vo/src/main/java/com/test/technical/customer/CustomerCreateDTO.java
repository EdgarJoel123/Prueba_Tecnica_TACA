package com.test.technical.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreateDTO {

    @NotNull(message = "name is required")
    private String name;
    @NotNull(message = "gender is required")
    @Size(max = 11, message = "ci gender is too long")
    private String gender;
    @NotNull(message = "age is required")
    private Integer age;
    @NotNull(message = "ci is required")
    @Size(max = 11, message = "ci Name is too long")
    private String ci;
    @NotNull(message = "Person is required")
    private String address;
    @NotNull(message = "Person is required")
    private String phone;

    @JsonIgnore
    private Long personID;
    @JsonIgnore
    @Size(max = 1, message = "status Name is too long")
    private String status;
    @NotNull(message = "password is required")
    private String password;

}
