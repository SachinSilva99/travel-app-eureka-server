package com.sachin.travelservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sachin.travelservice.entity.enums.PackageCategory;
import jakarta.validation.constraints.*;
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
public class TravelDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String travelId;
    @FutureOrPresent
    private LocalDate startDate;
    @FutureOrPresent
    private LocalDate endDate;

    @Min(value = 1, message = "The number of adults must be at least 1")
    private int noOfAdults;
    @Min(value = 0, message = "The number of children must be 0 or greater than 0")
    private int noOfChildren;
    @Min(value = 1, message = "Total Head Counts must be at least 1")
    private int totalHeadCount;
    @NotNull
    private Boolean isWithPets;
    private boolean isWithGuide;
    private boolean isCancelled;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean isApprovedByAdmin;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String bankSlipImg;

    private String guideId;

    @DecimalMin(value = "1.0", message = "The total price must be greater than or equal to 1")
    private double travelTotalPrice;
    @NotNull
    private LocalDate travelPlacedDate;
    @NotEmpty
    private List<HotelStayDto> hotelStayDtos;

    @NotEmpty
    private String vehicleId;

    private PackageCategory packageCategory;
    @NotEmpty
    private String userId;
}
