package com.sachin.travelservice.api;

import com.sachin.travelservice.dto.TravelDTO;
import com.sachin.travelservice.dto.UserDTO;
import com.sachin.travelservice.dto.fulldetail.GuideDTO;
import com.sachin.travelservice.dto.fulldetail.HotelStayFullDetailDto;
import com.sachin.travelservice.dto.fulldetail.TravelFullDetailDto;
import com.sachin.travelservice.dto.fulldetail.VehicleDTO;
import com.sachin.travelservice.service.APIService;
import com.sachin.travelservice.service.TravelService;
import com.sachin.travelservice.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/gettravels", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@CrossOrigin("*")
public class GetTravelController {
    private final TravelService travelService;
    private final APIService apiService;
    @GetMapping(value = "/{travelId}")
    public ResponseEntity<StandardResponse<TravelFullDetailDto>> getTravelFullDto(
            @PathVariable String travelId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        System.out.println(authorizationHeader);
        String bearerToken = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            bearerToken = authorizationHeader.substring(7);
        }
        String userApiUrl = "http://localhost:8090/userservice/api/v1/getusers";
        String hotelApiUrl = "http://localhost:8092/hotelservice/api/v1/gethotels";
        String vehicleApiUrl = "http://localhost:8095/vehicleservice/api/v1/getvehicles";
        String guideApiUrl = "http://localhost:8097/guideservice/api/v1/getguides";
        String guideId = travelService.getTravel(travelId).getGuideId();
        GuideDTO guideDTO = null;
        if (guideId != null) {
            guideDTO = apiService.getGuideDTO(guideApiUrl, travelId);
        }

        UserDTO userDTO = apiService.getUserDTO(userApiUrl, travelId, bearerToken);
        List<HotelStayFullDetailDto> hotelStayDtos = apiService.getHotelStayDtos(hotelApiUrl, travelId);
        VehicleDTO vehicleDTO = apiService.getVehcileDto(vehicleApiUrl, travelId);
        System.out.println(guideDTO);

        TravelFullDetailDto travelFullDetailDto = travelService.getFullTravelDto(travelId,userDTO, hotelStayDtos, vehicleDTO, guideDTO);
        return new ResponseEntity<>(
                new StandardResponse<>(HttpStatus.OK.value(), "OK", travelFullDetailDto),
                HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<StandardResponse<List<TravelDTO>>> getAll(){
        List<TravelDTO> travelDTOS = travelService.getAll();
        return new ResponseEntity<>(
                new StandardResponse<>(HttpStatus.OK.value(), "OK", travelDTOS),
                HttpStatus.OK);
    }
    @GetMapping("userid/{userId}")
    public ResponseEntity<StandardResponse<List<TravelDTO>>> getAllPerCustomer(@PathVariable String userId){
        List<TravelDTO> travelDTOS = travelService.getAllPerCustomer(userId);
        return new ResponseEntity<>(
                new StandardResponse<>(HttpStatus.OK.value(), "OK", travelDTOS),
                HttpStatus.OK);
    }

    @GetMapping("/test")
    public String get(){
        return "hehe";
    }
}
