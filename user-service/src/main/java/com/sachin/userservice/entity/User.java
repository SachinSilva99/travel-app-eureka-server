package com.sachin.userservice.entity;

import com.sachin.userservice.entity.enums.GENDER;
import com.sachin.userservice.entity.enums.USER_TYPE;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class User implements UserDetails {

    @Id
    private String userId;

    @NotNull
    @Indexed(unique = true)
    private String username;
    @NotBlank
    private String password;

    @Email
    @Indexed(unique = true)
    private String email;

    @NotNull
    @Indexed(unique = true)
    private String nicPassportNumber;

    @NotNull
    private String address;

    @NotNull
    @Indexed(unique = true)
    private String phoneNumber;

    @NotNull
    private String profilePicture;

    @NotNull
    private String nicPassportFrontImg;

    @NotNull
    private String nicPassportBackImg;

    @NotNull
    private USER_TYPE userType;

    @NotNull
    private GENDER gender;

    @NotNull
    private String remarks;

    @NotNull
    private LocalDate dob;

    @DBRef
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(userType.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword(){
        return password;
    }
}
