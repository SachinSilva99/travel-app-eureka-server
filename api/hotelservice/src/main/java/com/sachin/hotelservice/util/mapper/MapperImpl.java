package com.sachin.hotelservice.util.mapper;

import com.sachin.hotelservice.dto.HotelDTO;
import com.sachin.hotelservice.dto.HotelImageDTO;
import com.sachin.hotelservice.dto.HotelPackageDTO;
import com.sachin.hotelservice.entity.Hotel;
import com.sachin.hotelservice.entity.HotelImage;
import com.sachin.hotelservice.entity.HotelPackage;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MapperImpl implements Mapper {
    private final ModelMapper mapper;

    @Override
    public Hotel toHotel(HotelDTO hotelDTO) {
        return mapper.map(hotelDTO, Hotel.class);
    }

    @Override
    public HotelDTO toHotelDto(Hotel hotel) {
        HotelDTO hotelDTO = mapper.map(hotel, HotelDTO.class);
        List<HotelPackageDTO> hotelPackageDTOS = hotel.getHotelPackageList().stream().map(hotelPackage -> {
            HotelPackageDTO hotelPackageDTO = mapper.map(hotelPackage, HotelPackageDTO.class);
            hotelPackageDTO.setHotelId(hotelPackage.getHotel().getHotelId());
            return hotelPackageDTO;
        }).toList();

        List<String> hotelImageStrings = hotel.getHotelImages().stream().map(hotelImage -> {
            HotelImageDTO hotelImageDTO = mapper.map(hotelImage, HotelImageDTO.class);
            hotelImageDTO.setHotelId(hotelImage.getHotel().getHotelId());
            return hotelImageDTO.getHotelImgValue();
        }).toList();

        hotelDTO.setHotelImagesStrings(hotelImageStrings);
        hotelDTO.setHotelPackageDTOS(hotelPackageDTOS);
        return hotelDTO;
    }

    @Override
    public HotelPackageDTO toHotelPackageDto(HotelPackage hotelPackage) {
        return mapper.map(hotelPackage, HotelPackageDTO.class);
    }

    @Override
    public HotelPackage toHotelPackage(HotelPackageDTO hotelPackageDTO) {
        HotelPackage hotelPackage = mapper.map(hotelPackageDTO, HotelPackage.class);
        return hotelPackage;
    }

    @Override
    public HotelImageDTO toHotelImageDto(HotelImage hotelImage) {
        return mapper.map(hotelImage, HotelImageDTO.class);
    }

    @Override
    public HotelImage toHotelImage(HotelImageDTO hotelImageDTO) {
        return mapper.map(hotelImageDTO, HotelImage.class);
    }
}
