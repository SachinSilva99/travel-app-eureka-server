package com.sachin.travelservice.service.impl;

import com.sachin.travelservice.dto.HotelStayDto;
import com.sachin.travelservice.dto.TravelDTO;
import com.sachin.travelservice.dto.UserDTO;
import com.sachin.travelservice.dto.fulldetail.GuideDTO;
import com.sachin.travelservice.dto.fulldetail.HotelStayFullDetailDto;
import com.sachin.travelservice.dto.fulldetail.TravelFullDetailDto;
import com.sachin.travelservice.dto.fulldetail.VehicleDTO;
import com.sachin.travelservice.entity.HotelStay;
import com.sachin.travelservice.entity.Travel;
import com.sachin.travelservice.exception.NotFoundException;
import com.sachin.travelservice.exception.UpdateNotAllowedException;
import com.sachin.travelservice.repo.HotelStayRepo;
import com.sachin.travelservice.repo.TravelRepo;
import com.sachin.travelservice.service.TravelService;
import com.sachin.travelservice.util.CustomTravelIdGenerator;
import com.sachin.travelservice.util.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TravelServiceImpl implements TravelService {
    private final TravelRepo travelRepo;
    private final HotelStayRepo hotelStayRepo;
    private final Mapper mapper;
    private final CustomTravelIdGenerator idGenerator;

    @Override
    public String createTravel(TravelDTO travelDTO) {
        Travel travel = mapper.toTravel(travelDTO);

        String travelId = idGenerator.generateId(travelDTO.getUserId(), travelDTO.getTravelPlacedDate());
        while (travelRepo.existsById(travelId)) {
            travelId = idGenerator.generateId(travelDTO.getUserId(), travelDTO.getTravelPlacedDate());
        }
        travel.setCustomTravelId(travelId);

        List<HotelStay> hotelStays = travelDTO.getHotelStayDtos().stream()
                .map(mapper::toHotelStay).toList();
        travel.setHotelStays(hotelStays);

        hotelStayRepo.saveAll(hotelStays);
        return travelRepo.save(travel).getTravelId();
    }

    @Override
    public void updateTravel(String travelId, TravelDTO travelDTO) {


        Travel existingTravel = travelRepo.findById(travelId)
                .orElseThrow(() -> new NotFoundException("Travel with ID " + travelId + " not found"));

        LocalDateTime currentDateTime = LocalDateTime.now();

        long hoursDifference = ChronoUnit.HOURS.between(travelDTO.getTravelPlacedDate(), currentDateTime);


        if (hoursDifference > 48) {
            throw new UpdateNotAllowedException("Update is not allowed because the travel was placed more than 48 hours ago.");
        }
        existingTravel.setStartDate(travelDTO.getStartDate());
        existingTravel.setEndDate(travelDTO.getEndDate());
        existingTravel.setNoOfAdults(travelDTO.getNoOfAdults());
        existingTravel.setNoOfChildren(travelDTO.getNoOfChildren());
        existingTravel.setTotalHeadCount(travelDTO.getTotalHeadCount());
        existingTravel.setIsWithPets(travelDTO.getIsWithPets());
        existingTravel.setIsWithGuide(travelDTO.getIsWithPets());
        existingTravel.setCancelled(travelDTO.isCancelled());
        existingTravel.setTravelTotalPrice(travelDTO.getTravelTotalPrice());
        existingTravel.setVehicleId(travelDTO.getVehicleId());
        existingTravel.setUserId(travelDTO.getUserId());
        existingTravel.setGuideId(travelDTO.getGuideId());
        existingTravel.setPackageCategory(travelDTO.getPackageCategory());

        List<HotelStay> hotelStays = travelDTO.getHotelStayDtos().stream()
                .map(dto -> {
                    HotelStay hotelStay = mapper.toHotelStay(dto);
                    hotelStay.setTravelId(travelId);
                    return hotelStay;
                }).toList();
        existingTravel.setHotelStays(hotelStays);

        travelRepo.save(existingTravel);
    }

    @Override
    public void delete(String travelId) throws NotFoundException {
        Travel travel = travelRepo.findById(travelId)
                .orElseThrow(() -> new NotFoundException("Travel with ID " + travelId + " not found"));

        hotelStayRepo.deleteAll(travel.getHotelStays());
        travelRepo.delete(travel);
    }

    @Override
    public TravelDTO getTravel(String travelId) {
        Travel travel = travelRepo.findById(travelId)
                .orElseThrow(() -> new NotFoundException("Travel with ID " + travelId + " not found"));
        List<HotelStayDto> hotelStayDtos = travel.getHotelStays().stream().map(mapper::toHotelStayDto).toList();
        TravelDTO travelDto = mapper.toTravelDto(travel);
        travelDto.setHotelStayDtos(hotelStayDtos);
        return travelDto;
    }

    public double calculateTotalPriceForNonCancelledTravelsOnDate(LocalDate date) {
        List<Travel> nonCancelledTravels = travelRepo.findNonCancelledTravelsOnDate(date);
        return nonCancelledTravels.stream()
                .mapToDouble(Travel::getTravelTotalPrice)
                .sum();
    }

    public double calculateTotalPriceForNonCancelledTravelsInWeek(LocalDate startDate) {
        LocalDate endOfWeek = startDate.plusDays(6);

        List<Travel> nonCancelledTravels = travelRepo.findNonCancelledTravelsInWeek(startDate, endOfWeek);
        return nonCancelledTravels.stream()
                .mapToDouble(Travel::getTravelTotalPrice)
                .sum();
    }

    public double calculateTotalPriceForNonCancelledTravelsInMonth(int year, int month) {
        LocalDate startOfMonth = LocalDate.of(year, month, 1);
        LocalDate endOfMonth = startOfMonth.withDayOfMonth(startOfMonth.lengthOfMonth());

        List<Travel> nonCancelledTravels = travelRepo.findNonCancelledTravelsInMonth(startOfMonth, endOfMonth);
        return nonCancelledTravels.stream()
                .mapToDouble(Travel::getTravelTotalPrice)
                .sum();
    }

    public double calculateTotalPriceForNonCancelledTravelsInYear(int year) {
        LocalDate startOfYear = LocalDate.of(year, 1, 1);
        LocalDate endOfYear = LocalDate.of(year, 12, 31);

        List<Travel> nonCancelledTravels = travelRepo.findNonCancelledTravelsInYear(startOfYear, endOfYear);
        return nonCancelledTravels.stream()
                .mapToDouble(Travel::getTravelTotalPrice)
                .sum();
    }

    @Override
    public TravelFullDetailDto getFullTravelDto(String travelId, UserDTO userDTO, List<HotelStayFullDetailDto> hotelStayDtos, VehicleDTO vehicleDTO,
                                                GuideDTO guideDTO) {

        Travel travel = travelRepo.findById(travelId)
                .orElseThrow(() -> new NotFoundException("Travel with ID " + travelId + " not found"));
        return new TravelFullDetailDto(
                travel.getTravelId(),
                travel.getStartDate(),
                travel.getEndDate(),
                travel.getNoOfAdults(),
                travel.getNoOfChildren(),
                travel.getTotalHeadCount(),
                travel.getIsWithPets(),
                travel.getIsWithGuide(),
                travel.isCancelled(),
                guideDTO,
                travel.getTravelTotalPrice(),
                travel.getTravelPlacedDate(),
                travel.getBankSlipImg(),
                hotelStayDtos,
                vehicleDTO,
                travel.getPackageCategory(),
                userDTO
        );
    }

    @Override
    public List<TravelDTO> getAll() {
        return travelRepo.findAll().stream().map(mapper::toTravelDto).toList();
    }

    @Override
    public List<TravelDTO> getAllPerCustomer(String userId) {
        if (travelRepo.findAllByUserId(userId).isEmpty()) {
            throw new NotFoundException(userId + " not found");
        }
        return travelRepo.findAll().stream()
                .filter(travel -> travel.getUserId().equals(userId))
                .map(mapper::toTravelDto)
                .toList();
    }

}
