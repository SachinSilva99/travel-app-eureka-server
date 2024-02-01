package com.sachin.travelservice.dto.fulldetail;

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
public class HotelStayFullDetailDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String hotelStayId;
    @NotBlank
    private HotelDTO hotelDTO;
    @NotBlank
    private HotelPackageDTO hotelPackageDTO;
    private double lat;
    private double lng;

    @NotBlank
    private LocalDate hotelStayStartDate;
    @NotBlank
    private LocalDate hotelStayEndDate;
    @NotBlank
    private double hotelStayTotalCost;
    @NotBlank
    private String travelId;
}
