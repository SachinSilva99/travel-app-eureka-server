package com.sachin.vehicleservice.api;

import com.sachin.vehicleservice.dto.VehicleDTO;
import com.sachin.vehicleservice.exception.ImageFileException;
import com.sachin.vehicleservice.service.VehicleService;
import com.sachin.vehicleservice.util.StandardResponse;
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
@RequestMapping(value = "/api/v1/vehicles", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StandardResponse<String>> createVehicle(
            @RequestPart VehicleDTO vehicleDTO,
            @RequestPart MultipartFile vehicleMainImage,
            @RequestPart MultipartFile vehicleImgFront,
            @RequestPart MultipartFile vehicleImgBack,
            @RequestPart MultipartFile vehicleImgFrontInterior,
            @RequestPart MultipartFile vehicleImgBackInterior,
            @RequestPart MultipartFile driverDrivingLicenseFront,
            @RequestPart MultipartFile driverDrivingLicenseBack
    ) throws IOException {
        vehicleDTO.setVehicleMainImage(validateAndConvertToString(vehicleMainImage));
        vehicleDTO.setVehicleImgFront(validateAndConvertToString(vehicleImgFront));
        vehicleDTO.setVehicleImgBack(validateAndConvertToString(vehicleImgBack));
        vehicleDTO.setVehicleImgFrontInterior(validateAndConvertToString(vehicleImgFrontInterior));
        vehicleDTO.setDriverDrivingLicenseFront(validateAndConvertToString(vehicleImgBackInterior));


        vehicleDTO.setVehicleImgBackInterior(validateAndConvertToString(driverDrivingLicenseFront));
        vehicleDTO.setDriverDrivingLicenseBack(validateAndConvertToString(driverDrivingLicenseBack));
        System.out.println(vehicleDTO);
        String vehicleId = vehicleService.createVehicle(vehicleDTO);
        return new ResponseEntity<>(
                new StandardResponse<>(HttpStatus.CREATED.value(), "CREATED Vehicle", vehicleId),
                HttpStatus.CREATED);
    }

    @PutMapping(value = "/{vehicleId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StandardResponse<String>> createUpdate(
            @PathVariable String vehicleId,
            @RequestPart VehicleDTO vehicleDTO,
            @RequestPart(required = false) MultipartFile vehicleMainImage,
            @RequestPart(required = false) MultipartFile vehicleImgFront,
            @RequestPart(required = false) MultipartFile vehicleImgBack,
            @RequestPart(required = false) MultipartFile vehicleImgFrontInterior,
            @RequestPart(required = false) MultipartFile vehicleImgBackInterior,
            @RequestPart(required = false) MultipartFile driverDrivingLicenseFront,
            @RequestPart(required = false) MultipartFile driverDrivingLicenseBack
    ) throws IOException {
        setImagesIfNotNullInDto(
                vehicleDTO,
                vehicleMainImage,
                vehicleImgFront,
                vehicleImgBack,
                vehicleImgFrontInterior,
                vehicleImgBackInterior,
                driverDrivingLicenseFront,
                driverDrivingLicenseBack
        );
        vehicleService.updateVehicle(vehicleId, vehicleDTO);
        return new ResponseEntity<>(
                new StandardResponse<>(),
                HttpStatus.NO_CONTENT);
    }

    private void setImagesIfNotNullInDto(
            VehicleDTO vehicleDTO,
            MultipartFile vehicleMainImage,
            MultipartFile vehicleImgFront,
            MultipartFile vehicleImgBack,
            MultipartFile vehicleImgFrontInterior,
            MultipartFile vehicleImgBackInterior,
            MultipartFile driverDrivingLicenseFront,
            MultipartFile driverDrivingLicenseBack) throws IOException {
        if (vehicleMainImage != null) {
            vehicleDTO.setVehicleMainImage(validateAndConvertToString(vehicleMainImage));
        }
        if (vehicleImgFront != null) {
            vehicleDTO.setVehicleImgFront(validateAndConvertToString(vehicleImgFront));
        }
        if (vehicleImgBack != null) {
            vehicleDTO.setVehicleImgBack(validateAndConvertToString(vehicleImgBack));
        }
        if (vehicleImgFrontInterior != null) {
            vehicleDTO.setVehicleImgFrontInterior(validateAndConvertToString(vehicleImgFrontInterior));
        }
        if (vehicleImgBackInterior != null) {
            vehicleDTO.setVehicleImgBackInterior(validateAndConvertToString(vehicleImgBackInterior));
        }
        if (driverDrivingLicenseFront != null) {
            vehicleDTO.setDriverDrivingLicenseFront(validateAndConvertToString(driverDrivingLicenseFront));
        }
        if (driverDrivingLicenseBack != null) {
            vehicleDTO.setDriverDrivingLicenseBack(validateAndConvertToString(driverDrivingLicenseBack));
        }
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<StandardResponse<VehicleDTO>> getVehicle(@PathVariable String vehicleId) {
        VehicleDTO vehicle = vehicleService.getVehicle(vehicleId);
        return new ResponseEntity<>(
                new StandardResponse<>(HttpStatus.OK.value(), "OK", vehicle),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<StandardResponse<VehicleDTO>> deleteVehicle(@PathVariable String vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
        return new ResponseEntity<>(
                new StandardResponse<>(),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<StandardResponse<List<VehicleDTO>>> GetALl() {
        List<VehicleDTO> vehicleDTOS = vehicleService.getAll();
        return new ResponseEntity<>(
                new StandardResponse<>(HttpStatus.OK.value(), "OK", vehicleDTOS),
                HttpStatus.CREATED);
    }

    private String validateAndConvertToString(MultipartFile file) throws IOException {
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new ImageFileException("Invalid image file. Only image files are allowed.");
        }
        return Base64.getEncoder().encodeToString(file.getBytes());
    }
}
