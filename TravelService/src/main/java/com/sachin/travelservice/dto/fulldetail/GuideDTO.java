package com.sachin.travelservice.dto.fulldetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sachin.travelservice.dto.enums.user.GENDER;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GuideDTO {
    private String guideId;

    @NotEmpty
    private String guideName;

    @NotNull
    private LocalDate dob;

    private GENDER gender;

    @NotEmpty
    private String contact;

    private String guideProfileImage;

    private String guideIdImgFront;

    private String guideIdImgBack;

    @NotEmpty
    private String guideExperience;

    @NotEmpty
    private String guide_remarks;
    private double guideManDayValue;
}
