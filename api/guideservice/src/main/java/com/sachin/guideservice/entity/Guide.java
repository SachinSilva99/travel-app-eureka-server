package com.sachin.guideservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sachin.guideservice.entity.enums.GENDER;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
@Builder
public class Guide {
    @Id
    private String guideId;

    @NotEmpty
    private String guideName;

    @NotNull
    private LocalDate dob;


    private GENDER gender;

    @NotEmpty
    private String contact;

    @NotEmpty
    private String guideProfileImage;
    @NotEmpty
    private String guideIdImgFront;

    @NotEmpty
    private String guideIdImgBack;

    @NotEmpty
    private String guideExperience;

    @NotEmpty
    private String guide_remarks;

    private double guideManDayValue;
}
