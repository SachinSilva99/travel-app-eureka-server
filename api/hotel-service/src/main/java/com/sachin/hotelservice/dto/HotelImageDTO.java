package com.sachin.hotelservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelImageDTO {
    private String hotelImageId;
    private String hotelImgValue;
    private String hotelId;
}
