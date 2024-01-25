package com.sachin.hotelservice.api;

import com.sachin.hotelservice.dto.HotelDTO;
import com.sachin.hotelservice.entity.enums.HotelCategory;
import com.sachin.hotelservice.exception.InValidDataException;
import com.sachin.hotelservice.service.HotelService;
import com.sachin.hotelservice.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<StandardResponse<String>> createHotel(
            @RequestPart List<MultipartFile> hotelImagesRequest,
            @RequestPart HotelDTO hotelDTO
    ) {
        System.out.println("got to admin hotel");

        System.out.println(hotelImagesRequest.size());
        hotelImagesRequest.forEach(multipartFile -> {
            try {
                String imageString = Base64.getEncoder().encodeToString(multipartFile.getBytes());
                hotelDTO.getHotelImagesStrings().add(imageString);
            } catch (IOException e) {
                throw new InValidDataException("Something wrong in hotel images");
            }
        });


        String hotelId = hotelService.createHotel(hotelDTO);
        return new ResponseEntity<>(
                new StandardResponse<>(HttpStatus.CREATED.value(), hotelId + " Created Hotel", hotelId),
                HttpStatus.CREATED);
    }

    @GetMapping(value = "/{hotelId}")
    public ResponseEntity<StandardResponse<HotelDTO>> getHotel(@PathVariable String hotelId) {
        return new ResponseEntity<>(
                new StandardResponse<>(HttpStatus.OK.value(), "OK", hotelService.getHotel(hotelId)),
                HttpStatus.OK);
    }

    @DeleteMapping(value = "/images/{imageId}")
    public ResponseEntity<StandardResponse<HotelDTO>> deleteImage(@PathVariable String imageId) {
        hotelService.deleteHotelImage(imageId);
        return new ResponseEntity<>(
                new StandardResponse<>(HttpStatus.OK.value(), "OK", null),
                HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<StandardResponse<List<HotelDTO>>> getHotels() {
        return new ResponseEntity<>(
                new StandardResponse<>(HttpStatus.OK.value(), "OK", hotelService.getHotelDtos()),
                HttpStatus.OK);
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<StandardResponse<String>> deleteHotel(@PathVariable String hotelId) {
        hotelService.delete(hotelId);
        return new ResponseEntity<>(
                new StandardResponse<>(),
                HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{hotelId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StandardResponse<String>> updateHotel(
            @RequestPart(required = false) List<MultipartFile> hotelImagesRequest,
            @RequestPart HotelDTO hotelDTO,
            @PathVariable String hotelId) {
        if (hotelImagesRequest != null) {
            hotelImagesRequest.forEach(multipartFile -> {
                try {
                    String imageString = Base64.getEncoder().encodeToString(multipartFile.getBytes());
                    hotelDTO.getHotelImagesStrings().add(imageString);
                } catch (IOException e) {
                    throw new InValidDataException("Something wrong in hotel images");
                }
            });
        }


        hotelService.update(hotelId, hotelDTO);
        return new ResponseEntity<>(
                new StandardResponse<>(),
                HttpStatus.NO_CONTENT);
    }

    @GetMapping("/category/{hotelCategory}")
    public ResponseEntity<StandardResponse<List<HotelDTO>>> getHotelsWithCategory(
            @PathVariable HotelCategory hotelCategory) {
        return new ResponseEntity<>(
                new StandardResponse<>(
                        HttpStatus.OK.value(),
                        "OK",
                        hotelService.findHotelsWithCategory(hotelCategory)
                ),
                HttpStatus.OK);
    }

    @GetMapping("/cancellationCriteria/{isCancellationCriteria}")
    public ResponseEntity<StandardResponse<List<HotelDTO>>> getHotelsWithCancellationCriteria(
            @PathVariable boolean isCancellationCriteria) {
        return new ResponseEntity<>(
                new StandardResponse<>(
                        HttpStatus.OK.value(),
                        "OK",
                        hotelService.findHotelsWithIsCancellationCriteria(isCancellationCriteria)
                ),
                HttpStatus.OK);
    }

    @GetMapping("/byHotelCategoryPetsAllowedCancellationCriteria/{hotelCategory}/{isCancellationCriteria}/{isPetsAllowed}")
    public ResponseEntity<StandardResponse<List<HotelDTO>>> getHotelsByHotelCategoryPetsAllowedCancellationCriteria(
            @PathVariable HotelCategory hotelCategory,
            @PathVariable boolean isCancellationCriteria,
            @PathVariable boolean isPetsAllowed) {
        return new ResponseEntity<>(
                new StandardResponse<>(
                        HttpStatus.OK.value(),
                        "OK",
                        hotelService.
                                findHotelsByHotelCategoryPetsAllowedCancellationCriteria(
                                        hotelCategory,
                                        isCancellationCriteria,
                                        isPetsAllowed
                                )
                ),
                HttpStatus.OK);
    }

}


