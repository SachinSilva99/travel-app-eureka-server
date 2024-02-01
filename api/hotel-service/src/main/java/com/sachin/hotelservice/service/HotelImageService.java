package com.sachin.hotelservice.service;


import com.sachin.hotelservice.dto.HotelImageDTO;

import java.util.List;

public interface HotelImageService {
    String createHotelImage(String hotelId, HotelImageDTO hotelImageDTO);
    void deleteHotelImage(String hotelImageId);

    void updateHotelImage(String hotelImageId, HotelImageDTO hotelImageDTO);

    List<HotelImageDTO> getALlHotelImageDtos();
}
