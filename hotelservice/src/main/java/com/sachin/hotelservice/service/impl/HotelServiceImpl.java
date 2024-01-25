package com.sachin.hotelservice.service.impl;

import com.sachin.hotelservice.dto.HotelDTO;
import com.sachin.hotelservice.dto.HotelImageDTO;
import com.sachin.hotelservice.entity.Hotel;
import com.sachin.hotelservice.entity.HotelImage;
import com.sachin.hotelservice.entity.HotelPackage;
import com.sachin.hotelservice.entity.enums.HotelCategory;
import com.sachin.hotelservice.exception.NotFoundException;
import com.sachin.hotelservice.repo.HotelImageRepo;
import com.sachin.hotelservice.repo.HotelPackageRepo;
import com.sachin.hotelservice.repo.HotelRepo;
import com.sachin.hotelservice.service.HotelService;
import com.sachin.hotelservice.util.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepo hotelRepo;
    private final HotelImageRepo hotelImageRepo;
    private final HotelPackageRepo hotelPackageRepo;
    private final Mapper mapper;

    @Override
    public String createHotel(HotelDTO hotelDTO) {

        Hotel hotel = mapper.toHotel(hotelDTO);
        String hotel_id = hotelRepo.save(hotel).getHotelId();

        hotelDTO.getHotelImagesStrings().forEach(imageString -> {
            HotelImage hotelImage = HotelImage.builder()
                    .hotelImgValue(imageString)
                    .hotel(hotel).build();
            hotelImageRepo.save(hotelImage);
            hotel.getHotelImages().add(hotelImage);

        });
        hotelDTO.getHotelPackageDTOS().forEach(hotelPackageDTO -> {
            HotelPackage hotelPackage = mapper.toHotelPackage(hotelPackageDTO);
            hotel.getHotelPackageList().add(hotelPackage);
            hotelPackage.setHotel(hotel);
            hotelPackageRepo.save(hotelPackage);
        });
        return hotel_id;
    }

    @Override
    public HotelDTO getHotel(String hotelId) {
        Optional<Hotel> byId = hotelRepo.findById(hotelId);
        if (byId.isEmpty()) {
            throw new NotFoundException("hotel id : " + hotelId + " not found");
        }
        Hotel hotel = byId.get();
        List<HotelImageDTO> hotelImageDTOS = hotel.getHotelImages().stream().map(hotelImage -> {
            HotelImageDTO hotelImageDto = mapper.toHotelImageDto(hotelImage);
            hotelImageDto.setHotelId(hotelImage.getHotel().getHotelId());
            hotelImageDto.setHotelImageId(hotelImage.getHotelImageId());
            return hotelImageDto;
        }).toList();
        HotelDTO hotelDto = mapper.toHotelDto(hotel);
        hotelDto.setHotelImageDTOS(hotelImageDTOS);
        return hotelDto;
    }


    @Override
    public void update(String hotelId, HotelDTO hotelDTO) {
        Optional<Hotel> byId = hotelRepo.findById(hotelId);
        if (byId.isEmpty()) {
            throw new NotFoundException("hotel id : " + hotelId + " not found");
        }
        Hotel hotel = byId.get();
        hotel.setHotelName(hotelDTO.getHotelName());
        hotel.setHotelCategory(hotelDTO.getHotelCategory());

        hotel.setHotelLocationLat(hotelDTO.getHotelLocationLat());
        hotel.setHotelLocationLng(hotelDTO.getHotelLocationLng());
        hotel.setHotelLocation(hotelDTO.getHotelLocation());
        hotel.setHotelEmail(hotelDTO.getHotelEmail());
        hotel.setHotelContactNumber(hotelDTO.getHotelContactNumber());
        hotel.setIsHotelPetsAllowed(hotelDTO.getIsHotelPetsAllowed());
        hotel.setIsHotelCancellationCriteriaFree(hotelDTO.getIsHotelCancellationCriteriaFree());
        hotel.setHotelCancellationCost(hotelDTO.getHotelCancellationCost());
        hotel.setHotelRemarks(hotelDTO.getHotelRemarks());

        //if only images
        if (hotelDTO.getHotelImagesStrings() != null) {
            hotelDTO.getHotelImagesStrings().forEach(imageString -> {
                HotelImage hotelImage = HotelImage.builder()
                        .hotelImgValue(imageString)
                        .hotel(hotel).build();
                hotelImageRepo.save(hotelImage);
                hotel.getHotelImages().add(hotelImage);

            });
        }

        if (hotelDTO.getHotelPackageDTOS() != null) {
            hotelDTO.getHotelPackageDTOS().forEach(hotelPackageDTO -> {
                HotelPackage hotelPackage = mapper.toHotelPackage(hotelPackageDTO);
                hotel.getHotelPackageList().add(hotelPackage);
                hotelPackage.setHotel(hotel);
                hotelPackageRepo.save(hotelPackage);
            });
        }
        hotelRepo.save(hotel);

    }

    @Override
    public void delete(String hotelId) {
        Optional<Hotel> byId = hotelRepo.findById(hotelId);
        if (byId.isEmpty()) {
            throw new NotFoundException("hotel id : " + hotelId + " not found");
        }
        String hotel_id = byId.get().getHotelId();
        hotelRepo.deleteById(hotel_id);
    }

    @Override
    public List<HotelDTO> getHotelDtos() {
        return hotelRepo.findAll().stream().map(hotel -> {
            List<HotelImageDTO> hotelImageDTOS = hotel.getHotelImages().stream().map(hotelImage -> {
                System.out.println("img :" + hotelImage.getHotelImageId());
                HotelImageDTO hotelImageDto = mapper.toHotelImageDto(hotelImage);
                hotelImageDto.setHotelId(hotelImage.getHotel().getHotelId());
                hotelImageDto.setHotelImageId(hotelImage.getHotelImageId());
                return hotelImageDto;
            }).toList();
            HotelDTO hotelDto = mapper.toHotelDto(hotel);
            hotelDto.setHotelImageDTOS(hotelImageDTOS);
            return hotelDto;
        }).toList();

    }

    @Override
    public List<HotelDTO> findHotelsWithCategory(HotelCategory hotelCategory) {
        return hotelRepo.findAllByHotelCategory(hotelCategory).stream().map(mapper::toHotelDto).toList();
    }

    @Override
    public List<HotelDTO> findHotelsWithIsPetsAllowed(boolean isPetsAllowed) {
        return hotelRepo.findAllByIsHotelPetsAllowed(isPetsAllowed).stream().map(mapper::toHotelDto).toList();
    }

    @Override
    public List<HotelDTO> findHotelsWithIsCancellationCriteria(boolean isCancellationCriteriaFree) {
        return hotelRepo
                .findAllByIsHotelCancellationCriteriaFree(isCancellationCriteriaFree)
                .stream()
                .map(mapper::toHotelDto)
                .toList();
    }

    @Override
    public List<HotelDTO> findHotelsByHotelCategoryPetsAllowedCancellationCriteria(
            HotelCategory hotelCategory, Boolean isHotelCancellationCriteria, Boolean isHotelPetsAllowed) {
        return hotelRepo
                .findAllByHotelCategoryAndIsHotelCancellationCriteriaFreeAndIsHotelPetsAllowed(
                        hotelCategory,
                        isHotelCancellationCriteria,
                        isHotelPetsAllowed).stream().map(mapper::toHotelDto).toList();
    }

    @Override
    public void deleteHotelImage(String imageId) {
        HotelImage hotelImage =
                hotelImageRepo.findById(imageId)
                        .orElseThrow(() -> new NotFoundException("hotel Image not found :" + imageId));
        hotelImageRepo.delete(hotelImage);

    }

/*    private HotelDTO getHotelDTO(Hotel hotel) {
        HotelDTO hotelDto = mapper.toHotelDto(hotel);

        List<String> hotelImageStrings = hotel.getHotelImages().stream().map(HotelImage::getHotelImgValue).toList();

        List<HotelPackageDTO> hotelPackageDTOS =
                hotel.getHotelPackageList().stream().map(mapper::toHotelPackageDto).toList();

        hotelDto.setHotelPackageDTOS(hotelPackageDTOS);
        hotelDto.setHotelImagesStrings(hotelImageStrings);
        return hotelDto;
    }*/
}
