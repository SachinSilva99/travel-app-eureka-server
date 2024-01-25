package com.sachin.travelservice.util.mapper;

import com.sachin.travelservice.dto.HotelStayDto;
import com.sachin.travelservice.dto.TravelDTO;
import com.sachin.travelservice.entity.HotelStay;
import com.sachin.travelservice.entity.Travel;

public interface Mapper {
    TravelDTO toTravelDto(Travel travel);

    Travel toTravel(TravelDTO travelDTO);

    HotelStay toHotelStay(HotelStayDto hotelStayDto);

    HotelStayDto toHotelStayDto(HotelStay hotelStay);
}
