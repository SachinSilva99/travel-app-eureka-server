package com.sachin.userservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sachin.userservice.entity.enums.GENDER;
import com.sachin.userservice.entity.enums.USER_TYPE;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Valid

public class UserDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String userId;
    @NotBlank
    @Size(min = 6, max = 50, message = "username must be minimum 6 characters and maximum 50")
    private String username;

    @NotBlank
    private String password;
    @Email
    private String email;
    @NotBlank
    @Size(min = 9, max = 10, message = "nicPassportNumber must be minimum 8 characters and maximum 50")
    private String nicPassportNumber;
    @NotBlank
    @Size(min = 8, message = "address must be minimum 8 characters")
    private String address;
    @Size(min = 10, message = "address must be minimum 9 characters")
    private String phoneNumber;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String profilePicture;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String nicPassportFrontImg;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String nicPassportBackImg;
    @NotNull
    private USER_TYPE userType;
    @NotNull
    private GENDER gender;
    @NotBlank
    private String remarks;
    private LocalDate dob;
}
