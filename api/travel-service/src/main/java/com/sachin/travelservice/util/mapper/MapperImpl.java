package com.sachin.travelservice.util.mapper;

import com.sachin.travelservice.dto.HotelStayDto;
import com.sachin.travelservice.dto.TravelDTO;
import com.sachin.travelservice.entity.HotelStay;
import com.sachin.travelservice.entity.Travel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperImpl implements Mapper {
    private final ModelMapper mapper;

    @Override
    public TravelDTO toTravelDto(Travel travel) {
        return mapper.map(travel, TravelDTO.class);
    }

    @Override
    public Travel toTravel(TravelDTO travelDTO) {
        return mapper.map(travelDTO, Travel.class);
    }

    @Override
    public HotelStay toHotelStay(HotelStayDto hotelStayDto) {
        return mapper.map(hotelStayDto, HotelStay.class);
    }

    @Override
    public HotelStayDto toHotelStayDto(HotelStay hotelStay) {
        return mapper.map(hotelStay, HotelStayDto.class);
    }
}
