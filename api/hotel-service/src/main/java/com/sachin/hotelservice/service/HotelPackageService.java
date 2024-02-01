package com.sachin.hotelservice.service;

import com.sachin.hotelservice.dto.HotelPackageDTO;

import java.util.List;

public interface HotelPackageService {
    String createHotelPackage(String hotelId, HotelPackageDTO hotelPackageDTO);
    void deleteHotelPackage(String hotelPackageId);

    void updateHotelPackage(String hotelPackageId, HotelPackageDTO hotelPackageDTO);

    List<HotelPackageDTO> getALlHotelPackageDtos();

    HotelPackageDTO getHotelPackage(String hotelPackageId);
}
