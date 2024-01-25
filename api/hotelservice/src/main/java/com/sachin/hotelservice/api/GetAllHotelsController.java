package com.sachin.hotelservice.api;

import com.sachin.hotelservice.dto.HotelDTO;
import com.sachin.hotelservice.dto.HotelPackageDTO;
import com.sachin.hotelservice.service.HotelPackageService;
import com.sachin.hotelservice.service.HotelService;
import com.sachin.hotelservice.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/gethotels", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@CrossOrigin("*")
public class GetAllHotelsController {

    private final HotelService hotelService;
    private final HotelPackageService hotelPackageService;

    @GetMapping
    public ResponseEntity<StandardResponse<List<HotelDTO>>> getAllHotels() {
        System.out.println(hotelService.getHotelDtos());
        return new ResponseEntity<>(new StandardResponse<>(
                200,
                "OK",
                hotelService.getHotelDtos()
        ), HttpStatus.OK);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<StandardResponse<HotelDTO>> GetHotel(@PathVariable String hotelId) {
        return new ResponseEntity<>(new StandardResponse<>(
                200,
                "OK",
                hotelService.getHotel(hotelId)
        ), HttpStatus.OK);
    }

    @GetMapping("/hotelPackage/{hotelPackageId}")
    public ResponseEntity<StandardResponse<HotelPackageDTO>> getHotelPackage(@PathVariable String hotelPackageId) {
        return new ResponseEntity<>(new StandardResponse<>(
                200,
                "OK",
                hotelPackageService.getHotelPackage(hotelPackageId)
        ), HttpStatus.OK);
    }
}
