package com.sachin.travelservice.api;

import com.sachin.travelservice.dto.TravelDTO;
import com.sachin.travelservice.dto.UserDTO;
import com.sachin.travelservice.dto.fulldetail.GuideDTO;
import com.sachin.travelservice.dto.fulldetail.HotelStayFullDetailDto;
import com.sachin.travelservice.dto.fulldetail.TravelFullDetailDto;
import com.sachin.travelservice.dto.fulldetail.VehicleDTO;
import com.sachin.travelservice.exception.ImageFileException;
import com.sachin.travelservice.service.APIService;
import com.sachin.travelservice.service.TravelService;
import com.sachin.travelservice.util.StandardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/travels", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TravelController {
    private final TravelService travelService;
    private final APIService apiService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StandardResponse<String>> createTravel(
            @RequestPart @Valid TravelDTO travelDTO,
            @RequestPart MultipartFile bankSlipImg
    ) throws IOException {
        validateImageFile(bankSlipImg);
        String bankSlipImgString = encodeToBase64(bankSlipImg);
        travelDTO.setBankSlipImg(bankSlipImgString);
        String travelId = travelService.createTravel(travelDTO);
        return new ResponseEntity<>(
                new StandardResponse<>(HttpStatus.CREATED.value(), "Travel Created ", travelId),
                HttpStatus.CREATED);
    }

    @PutMapping(value = "/{travelId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StandardResponse<String>> updateTravel(
            @PathVariable String travelId,
            @RequestPart @Valid TravelDTO travelDTO,
            @RequestPart(required = false) MultipartFile bankSlipImg
    ) throws IOException {
        validateImageFile(bankSlipImg);
        String bankSlipImgString = encodeToBase64(bankSlipImg);
        travelDTO.setBankSlipImg(bankSlipImgString);
        travelService.updateTravel(travelId, travelDTO);
        return new ResponseEntity<>(new StandardResponse<>(), HttpStatus.NO_CONTENT);
    }


    @GetMapping(value = "/{travelId}")
    public ResponseEntity<StandardResponse<TravelFullDetailDto>> getTravelFullDto(
            @PathVariable String travelId,
            @RequestHeader("Authorization") String authorizationHeader // Get the Authorization header
    ) {
        String bearerToken = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            bearerToken = authorizationHeader.substring(7); // Remove "Bearer " prefix
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


    @GetMapping("/daily-income")
    public ResponseEntity<StandardResponse<Double>> getDailyIncome(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        double dailyIncome = travelService.calculateTotalPriceForNonCancelledTravelsOnDate(date);
        return new ResponseEntity<>(
                new StandardResponse<>(HttpStatus.OK.value(), "Daily income for " + date, dailyIncome),
                HttpStatus.OK);
    }

    @GetMapping("/monthly-income")
    public ResponseEntity<StandardResponse<Double>> getMonthlyIncome(@RequestParam("date") int year, @RequestParam int month) {

        double monthlyIncome = travelService.calculateTotalPriceForNonCancelledTravelsInMonth(year, month);

        return new ResponseEntity<>(
                new StandardResponse<>(
                        HttpStatus.OK.value(),
                        "Monthly income for year : " + year + " , month: " + month,
                        monthlyIncome),
                HttpStatus.OK);
    }

    @GetMapping("/yearly-income")
    public ResponseEntity<StandardResponse<Double>> getYearlyIncome(@RequestParam("date") int year) {
        double yearlyIncome = travelService.calculateTotalPriceForNonCancelledTravelsInYear(year);
        return new ResponseEntity<>(
                new StandardResponse<>(
                        HttpStatus.OK.value(),
                        "Monthly income for year : " + year,
                        yearlyIncome),
                HttpStatus.OK);
    }

    private String encodeToBase64(MultipartFile file) throws IOException {
        return Base64.getEncoder().encodeToString(file.getBytes());
    }

    private void validateImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new ImageFileException("Invalid image file. Only image files are allowed.");
        }
    }
}
