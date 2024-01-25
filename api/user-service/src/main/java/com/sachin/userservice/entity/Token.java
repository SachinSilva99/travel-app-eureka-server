package com.sachin.userservice.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
@Builder
public class Token {

    @Id
    public String id;

    @Indexed(unique = true)
    public String token;

    public boolean revoked;
    public boolean expired;


    @Field("userId")
    public String userId;
}