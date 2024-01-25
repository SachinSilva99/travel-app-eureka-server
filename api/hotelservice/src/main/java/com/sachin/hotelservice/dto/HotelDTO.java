package com.sachin.hotelservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sachin.hotelservice.entity.enums.HotelCategory;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String hotelId;

    @NotBlank
    private String hotelName;

    @NotNull
    private HotelCategory hotelCategory;

    @NotBlank
    private String hotelLocation;

  /*  @NotBlank
    private String hotelLocationCoordinates;*/

    @Email
    private String hotelEmail;

    @NotBlank
    private String hotelContactNumber;

    @NotNull
    private Boolean isHotelPetsAllowed;

    @NotNull
    private Boolean isHotelCancellationCriteriaFree;

    private double hotelCancellationCost;

    @NotBlank
    private String hotelRemarks;

    private double hotelLocationLat;

    private double hotelLocationLng;

    @NotEmpty
    private List<String> hotelImagesStrings = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)

    private List<HotelImageDTO> hotelImageDTOS = new ArrayList<>();

    @NotEmpty
    private List<HotelPackageDTO> hotelPackageDTOS = new ArrayList<>();

}
