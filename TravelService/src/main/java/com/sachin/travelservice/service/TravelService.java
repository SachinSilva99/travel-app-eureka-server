package com.sachin.travelservice.service;

import com.sachin.travelservice.dto.TravelDTO;
import com.sachin.travelservice.dto.UserDTO;
import com.sachin.travelservice.dto.fulldetail.GuideDTO;
import com.sachin.travelservice.dto.fulldetail.HotelStayFullDetailDto;
import com.sachin.travelservice.dto.fulldetail.TravelFullDetailDto;
import com.sachin.travelservice.dto.fulldetail.VehicleDTO;
import com.sachin.travelservice.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface TravelService {
    String createTravel(TravelDTO travelDTO);

    void updateTravel(String travelId, TravelDTO travelDTO) throws NotFoundException;

    void delete(String travelId) throws NotFoundException;

    TravelDTO getTravel(String travelId);


    double calculateTotalPriceForNonCancelledTravelsInWeek(LocalDate startDate);
    double calculateTotalPriceForNonCancelledTravelsOnDate(LocalDate date);
    double calculateTotalPriceForNonCancelledTravelsInMonth(int year, int month);
    public double calculateTotalPriceForNonCancelledTravelsInYear(int year);

    TravelFullDetailDto getFullTravelDto(
            String travelId, UserDTO userDTO, List<HotelStayFullDetailDto> hotelStayDtos, VehicleDTO vehicleDTO, GuideDTO guideDTO);

    List<TravelDTO> getAll();

    List<TravelDTO> getAllPerCustomer(String userId);
}
