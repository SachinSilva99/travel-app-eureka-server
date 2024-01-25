package com.sachin.vehicleservice.util.mapper;


import com.sachin.vehicleservice.dto.VehicleDTO;
import com.sachin.vehicleservice.entity.Vehicle;

public interface Mapper {
    Vehicle toVehicle(VehicleDTO vehicleDTO);
    VehicleDTO tVehicleDto(Vehicle vehicle);
}
