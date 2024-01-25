package com.sachin.travelservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HotelStayDto {

    private int hotelStayOrderNumber;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String hotelStayId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String travelId;

    @NotBlank
    private LocalDate hotelStayStartDate;
    @NotBlank
    private LocalDate hotelStayEndDate;
    @NotBlank
    private double hotelStayTotalCost;

    private double lat;
    private double lng;

    @NotBlank
    private String hotelStayHotelId;
    @NotEmpty
    private String hotelStayHotelPackageId;
}
