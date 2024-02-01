package com.sachin.hotelservice.service.impl;

import com.sachin.hotelservice.dto.HotelImageDTO;
import com.sachin.hotelservice.entity.Hotel;
import com.sachin.hotelservice.entity.HotelImage;
import com.sachin.hotelservice.exception.NotFoundException;
import com.sachin.hotelservice.repo.HotelImageRepo;
import com.sachin.hotelservice.repo.HotelRepo;
import com.sachin.hotelservice.service.HotelImageService;
import com.sachin.hotelservice.util.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelImageServiceImpl implements HotelImageService {
    private final HotelImageRepo imageRepo;
    private final HotelRepo hotelRepo;
    private final Mapper mapper;

    @Override
    public String createHotelImage(String hotelId, HotelImageDTO hotelImageDTO) {
        Optional<Hotel> hotelById = hotelRepo.findById(hotelId);
        if (hotelById.isEmpty()) {
            throw new NotFoundException("Hotel id " + hotelId + " not found");
        }
        HotelImage hotelImage = mapper.toHotelImage(hotelImageDTO);
        hotelImage.setHotel(hotelById.get());
        return imageRepo.save(hotelImage).getHotelImageId();
    }

    @Override
    public void deleteHotelImage(String hotelImageId) {
        if (!imageRepo.existsById(hotelImageId)) {
            throw new NotFoundException("Hotel image id " + hotelImageId + " not found");
        }
        imageRepo.deleteById(hotelImageId);
    }

    @Override
    public void updateHotelImage(String hotelImageId, HotelImageDTO hotelImageDTO) {
        Optional<HotelImage> hotelImageById = imageRepo.findById(hotelImageId);
        if (hotelImageById.isEmpty()) {
            throw new NotFoundException("Hotel Image id " + hotelImageId + " not found");
        }
        HotelImage hotelImage = hotelImageById.get();
        hotelImage.setHotelImgValue(hotelImageDTO.getHotelImgValue());
        imageRepo.save(hotelImage);
    }

    @Override
    public List<HotelImageDTO> getALlHotelImageDtos() {
        return imageRepo.findAll().stream().map(mapper::toHotelImageDto).toList();
    }
}
