package com.sachin.travelservice.dto.fulldetail;


import com.sachin.travelservice.dto.enums.hotel.HotelPackageRoomType;
import com.sachin.travelservice.dto.enums.hotel.HotelPackageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelPackageDTO {
    private String hotelPackageId;
    private HotelPackageType hotelPackageType;
    private HotelPackageRoomType hotelPackageRoomType;
    private double hotelPackagePrice;
    private String hotelId;
}
