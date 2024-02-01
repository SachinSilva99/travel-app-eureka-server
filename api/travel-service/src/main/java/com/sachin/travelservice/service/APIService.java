package com.sachin.travelservice.service;

import com.sachin.travelservice.dto.UserDTO;
import com.sachin.travelservice.dto.fulldetail.GuideDTO;
import com.sachin.travelservice.dto.fulldetail.HotelStayFullDetailDto;
import com.sachin.travelservice.dto.fulldetail.VehicleDTO;
import com.sachin.travelservice.exception.NotFoundException;

import java.util.List;

public interface APIService {
    UserDTO getUserDTO(String apiUrl, String travelId, String bearerToken) throws NotFoundException;


    List<HotelStayFullDetailDto> getHotelStayDtos(String hotelApiUrl, String travelId);

    VehicleDTO getVehcileDto(String vehicleApiUrl, String travelId) throws NotFoundException;

    GuideDTO getGuideDTO(String guideApiUrl, String travelId);
}
