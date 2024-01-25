package com.sachin.vehicleservice.service;


import com.sachin.vehicleservice.dto.VehicleDTO;
import com.sachin.vehicleservice.exception.NotFoundException;

import java.util.List;

public interface VehicleService {
    String createVehicle(VehicleDTO vehicleDTO);


    void deleteVehicle(String vehicleId) throws NotFoundException;

    List<VehicleDTO> getAll();

    VehicleDTO getVehicle(String vehicleId) throws NotFoundException;

    void updateVehicle(String vehicleId, VehicleDTO vehicleDTO) throws NotFoundException;
}
