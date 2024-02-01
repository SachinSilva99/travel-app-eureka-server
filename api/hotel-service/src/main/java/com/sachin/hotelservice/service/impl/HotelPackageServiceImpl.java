package com.sachin.hotelservice.service.impl;

import com.sachin.hotelservice.dto.HotelPackageDTO;
import com.sachin.hotelservice.entity.Hotel;
import com.sachin.hotelservice.entity.HotelPackage;
import com.sachin.hotelservice.exception.NotFoundException;
import com.sachin.hotelservice.repo.HotelPackageRepo;
import com.sachin.hotelservice.repo.HotelRepo;
import com.sachin.hotelservice.service.HotelPackageService;
import com.sachin.hotelservice.util.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelPackageServiceImpl implements HotelPackageService {
    private final HotelPackageRepo hotelPackageRepo;
    private final HotelRepo hotelRepo;
    private final Mapper mapper;

    @Override
    public String createHotelPackage(String hotelId, HotelPackageDTO hotelPackageDTO) {
        Optional<Hotel> hotelById = hotelRepo.findById(hotelId);
        if (hotelById.isEmpty()) {
            throw new NotFoundException("Hotel id " + hotelId + " not found");
        }
        HotelPackage hotelPackage = mapper.toHotelPackage(hotelPackageDTO);
        return hotelPackageRepo.save(hotelPackage).getHotelPackageId();
    }

    @Override
    public void deleteHotelPackage(String hotelPackageId) {
        if (!hotelPackageRepo.existsById(hotelPackageId)) {
            throw new NotFoundException("Hotel Package id " + hotelPackageId + " not found");
        }
        hotelPackageRepo.deleteById(hotelPackageId);
    }

    @Override
    public void updateHotelPackage(String hotelPackageId, HotelPackageDTO hotelImageDTO) {
        Optional<HotelPackage> hotelPackageById = hotelPackageRepo.findById(hotelPackageId);
        if (hotelPackageById.isEmpty()) {
            throw new NotFoundException("Hotel Package id " + hotelPackageId + " not found");
        }
        HotelPackage hotelPackage = hotelPackageById.get();
        hotelPackage.setHotelPackagePrice(hotelPackage.getHotelPackagePrice());
        hotelPackage.setHotelPackageType(hotelImageDTO.getHotelPackageType());
        hotelPackage.setHotelPackageRoomType(hotelImageDTO.getHotelPackageRoomType());
        hotelPackageRepo.save(hotelPackage);
    }

    @Override
    public List<HotelPackageDTO> getALlHotelPackageDtos() {
        return hotelPackageRepo.findAll().stream().map(mapper::toHotelPackageDto).toList();
    }

    @Override
    public HotelPackageDTO getHotelPackage(String hotelPackageId) {
        HotelPackage hotelPackage = hotelPackageRepo
                .findById(hotelPackageId)
                .orElseThrow(() -> new NotFoundException(hotelPackageId + " not found"));
        return mapper.toHotelPackageDto(hotelPackage);
    }
}
