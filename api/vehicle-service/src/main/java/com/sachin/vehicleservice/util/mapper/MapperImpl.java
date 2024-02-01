package com.sachin.vehicleservice.util.mapper;

import com.sachin.vehicleservice.dto.VehicleDTO;
import com.sachin.vehicleservice.entity.Vehicle;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperImpl implements Mapper {

    private final ModelMapper mapper;

    @Override
    public Vehicle toVehicle(VehicleDTO vehicleDTO) {
        return mapper.map(vehicleDTO, Vehicle.class);
    }

    @Override
    public VehicleDTO tVehicleDto(Vehicle vehicle) {
        return mapper.map(vehicle, VehicleDTO.class);
    }
}
