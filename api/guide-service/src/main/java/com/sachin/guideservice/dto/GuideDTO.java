package com.sachin.guideservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sachin.guideservice.entity.enums.GENDER;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GuideDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String guideId;

    @NotEmpty
    private String guideName;

    @NotNull
    private LocalDate dob;

    private GENDER gender;

    @NotEmpty
    private String contact;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String guideProfileImage;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String guideIdImgFront;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String guideIdImgBack;

    @NotEmpty
    private String guideExperience;

    @NotEmpty
    private String guide_remarks;
    private double guideManDayValue;
}
