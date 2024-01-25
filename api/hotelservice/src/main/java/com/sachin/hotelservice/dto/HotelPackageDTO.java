package com.sachin.hotelservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sachin.hotelservice.entity.enums.HotelPackageRoomType;
import com.sachin.hotelservice.entity.enums.HotelPackageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelPackageDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String hotelPackageId;
    private HotelPackageType hotelPackageType;
    private HotelPackageRoomType hotelPackageRoomType;
    private double hotelPackagePrice;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String hotelId;
}
