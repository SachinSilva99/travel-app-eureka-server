package com.sachin.travelservice.service.impl;

import com.sachin.travelservice.dto.UserDTO;
import com.sachin.travelservice.dto.fulldetail.*;
import com.sachin.travelservice.entity.HotelStay;
import com.sachin.travelservice.entity.Travel;
import com.sachin.travelservice.exception.NotFoundException;
import com.sachin.travelservice.repo.TravelRepo;
import com.sachin.travelservice.service.APIService;
import com.sachin.travelservice.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class APIServiceImpl implements APIService {

    private final TravelRepo travelRepo;
    private final WebClient webClient;

    @Override
    public UserDTO getUserDTO(String apiUrl, String travelId, String bearerToken) {
        String userId = travelRepo.findById(travelId)
                .orElseThrow(() -> new NotFoundException("Travel with ID " + travelId + " not found")).getUserId();

        WebClient webClient = WebClient.create(apiUrl + "/" + userId);
        Mono<StandardResponse<UserDTO>> standardResponseMono = webClient
                .get()
                .header("Authorization", "Bearer " + bearerToken)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
        StandardResponse<UserDTO> block = standardResponseMono.block();
        if (block == null) {
            throw new NotFoundException(userId + " user not found");
        }
        return block.getData();
    }

    @Override
    public List<HotelStayFullDetailDto> getHotelStayDtos(String hotelApiUrl, String travelId) {
        Travel travel = travelRepo.findById(travelId)
                .orElseThrow(() -> new NotFoundException("Travel with ID " + travelId + " not found"));


        List<HotelStay> hotelStays = travel.getHotelStays();
        return hotelStays.stream().map(hotelStay -> {

            String hotelId = hotelStay.getHotelStayHotelId();
            Mono<StandardResponse<HotelDTO>> standardResponseMono = webClient.get()
                    .uri(hotelApiUrl + "/" + hotelId)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<>() {
                    });
            StandardResponse<HotelDTO> block = standardResponseMono.block();
            if (block == null) {
                throw new NotFoundException(hotelId + " user not found");
            }
            HotelDTO hotelDTO = block.getData();
            System.out.println(hotelDTO.getHotelName() + " " + hotelDTO.getHotelId());
            String hotelStayHotelPackageId = hotelStay.getHotelStayHotelPackageId();
            HotelPackageDTO desiredPackage = hotelDTO.getHotelPackageDTOS()
                    .stream()
                    .filter(packageDTO -> hotelStayHotelPackageId.equals(packageDTO.getHotelPackageId()))
                    .findFirst()
                    .orElse(null);

            return HotelStayFullDetailDto.builder()
                    .hotelStayId(hotelStay.getHotelStayId())
                    .hotelPackageDTO(desiredPackage)
                    .hotelDTO(hotelDTO)
                    .travelId(travelId)
                    .lat(hotelStay.getLat())
                    .lng(hotelStay.getLng())
                    .hotelStayEndDate(hotelStay.getHotelStayEndDate())
                    .hotelStayStartDate(hotelStay.getHotelStayStartDate())
                    .hotelStayTotalCost(hotelStay.getHotelStayTotalCost()).build();
        }).toList();
    }

    @Override
    public VehicleDTO getVehcileDto(String vehicleApiUrl, String travelId) throws NotFoundException {
        Travel travel = travelRepo.findById(travelId)
                .orElseThrow(() -> new NotFoundException("Travel with ID " + travelId + " not found"));
        String vehicleId = travel.getVehicleId();
        Mono<StandardResponse<VehicleDTO>> standardResponseMono = webClient
                .get()
                .uri(vehicleApiUrl + "/" + vehicleId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
        StandardResponse<VehicleDTO> block = standardResponseMono.block();
        return block.getData();
    }

    @Override
    public GuideDTO getGuideDTO(String guideApiUrl, String travelId) {
        Travel travel = travelRepo.findById(travelId)
                .orElseThrow(() -> new NotFoundException("Travel with ID " + travelId + " not found"));
        String guideId = travel.getGuideId();
        Mono<StandardResponse<GuideDTO>> standardResponseMono = webClient
                .get()
                .uri(guideApiUrl + "/" + guideId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
        StandardResponse<GuideDTO> block = standardResponseMono.block();
        return block.getData();
    }
}
