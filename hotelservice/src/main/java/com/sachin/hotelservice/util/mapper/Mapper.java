package com.sachin.hotelservice.util.mapper;

import com.sachin.hotelservice.dto.HotelDTO;
import com.sachin.hotelservice.dto.HotelImageDTO;
import com.sachin.hotelservice.dto.HotelPackageDTO;
import com.sachin.hotelservice.entity.Hotel;
import com.sachin.hotelservice.entity.HotelImage;
import com.sachin.hotelservice.entity.HotelPackage;

public interface Mapper {
    Hotel toHotel(HotelDTO hotelDTO);
    HotelDTO toHotelDto(Hotel hotel);

    HotelPackageDTO toHotelPackageDto(HotelPackage hotelPackage);

    HotelPackage toHotelPackage(HotelPackageDTO hotelPackageDTO);

    HotelImageDTO toHotelImageDto(HotelImage hotelImage);

    HotelImage toHotelImage(HotelImageDTO hotelImageDTO);
}
