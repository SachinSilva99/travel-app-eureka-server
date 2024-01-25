package com.sachin.hotelservice.service;

import com.sachin.hotelservice.dto.HotelDTO;
import com.sachin.hotelservice.entity.enums.HotelCategory;

import java.util.List;

public interface HotelService {
    String createHotel(HotelDTO hotelDTO);

    HotelDTO getHotel(String hotelId);

    void update(String hotelId, HotelDTO hotelDTO);

    void delete(String hotelId);

    List<HotelDTO> getHotelDtos();

    List<HotelDTO> findHotelsWithCategory(HotelCategory hotelCategory);

    List<HotelDTO> findHotelsWithIsPetsAllowed(boolean isPetsAllowed);

    List<HotelDTO> findHotelsWithIsCancellationCriteria(boolean isPetsAllowed);

    List<HotelDTO> findHotelsByHotelCategoryPetsAllowedCancellationCriteria(
            HotelCategory hotelCategory, Boolean isHotelCancellationCriteria, Boolean isHotelPetsAllowed);

    void deleteHotelImage(String imageId);

}
