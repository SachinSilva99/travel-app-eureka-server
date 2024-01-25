package com.sachin.vehicleservice.api;

import com.sachin.vehicleservice.dto.VehicleDTO;
import com.sachin.vehicleservice.service.VehicleService;
import com.sachin.vehicleservice.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/getvehicles", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "Authorization")
public class GetVehicleController {
    private final VehicleService vehicleService;

    @GetMapping("/{vehicleId}")
    public ResponseEntity<StandardResponse<VehicleDTO>> getVehicle(@PathVariable String vehicleId) {
        VehicleDTO vehicle = vehicleService.getVehicle(vehicleId);
        return new ResponseEntity<>(
                new StandardResponse<>(HttpStatus.OK.value(), "OK", vehicle),
                HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<StandardResponse<List<VehicleDTO>>> getAllVehicle() {
        return new ResponseEntity<>(
                new StandardResponse<>(HttpStatus.OK.value(), "OK", vehicleService.getAll()
                ),
                HttpStatus.CREATED);
    }
}
