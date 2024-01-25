package com.sachin.travelservice.dto;

import com.sachin.travelservice.dto.enums.user.GENDER;
import com.sachin.travelservice.dto.enums.user.USER_TYPE;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Validated
public class UserDTO {
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
    private String profilePicture;
    private String nicPassportFrontImg;
    private String nicPassportBackImg;
    @NotNull
    private USER_TYPE userType;
    @NotNull
    private GENDER gender;
    @NotBlank
    private String remarks;
    private LocalDate dob;
}
