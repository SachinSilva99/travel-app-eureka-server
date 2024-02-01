package com.sachin.vehicleservice.service;

import com.sachin.vehicleservice.dto.VehicleDTO;
import com.sachin.vehicleservice.entity.Vehicle;
import com.sachin.vehicleservice.exception.NotFoundException;
import com.sachin.vehicleservice.repo.VehicleRepo;
import com.sachin.vehicleservice.util.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class VehicleServiceImpl implements VehicleService {
    private final Mapper mapper;
    private final VehicleRepo vehicleRepo;

    @Override
    public String createVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = mapper.toVehicle(vehicleDTO);
        return vehicleRepo.save(vehicle).getVehicleId();
    }

    @Override
    public void updateVehicle(String vehicleId, VehicleDTO dto) throws NotFoundException {
        Vehicle vehicle = vehicleRepo
                .findById(vehicleId)
                .orElseThrow(() -> new NotFoundException("vehicle id : " + vehicleId + " npt found"));

        vehicle.setVehicleBrand(dto.getVehicleBrand());
        if (dto.getVehicleMainImage() != null) {
            vehicle.setVehicleMainImage(dto.getVehicleMainImage());
        }
        vehicle.setVehicleCategory(dto.getVehicleCategory());
        vehicle.setVehicleName(dto.getVehicleName());
        vehicle.setFeeForOneDay100km(dto.getFeeForOneDay100km());
        vehicle.setFeeForExtra1km(dto.getFeeForExtra1km());
        vehicle.setVehicleRemarks(dto.getVehicleRemarks());
        vehicle.setVehicleDriverName(dto.getVehicleDriverName());
        vehicle.setVehicleDriverContact(dto.getVehicleDriverContact());
        vehicle.setVehicleTransmission(dto.getVehicleTransmission());
        vehicle.setVehicleFuelConsumption(dto.getVehicleFuelConsumption());

        if (dto.getVehicleMainImage() != null) {
            vehicle.setVehicleMainImage(dto.getVehicleMainImage());
        }
        if (dto.getVehicleImgFront() != null) {
            vehicle.setVehicleImgFront(dto.getVehicleImgFront());
        }
        if (dto.getVehicleImgBack() != null) {
            vehicle.setVehicleImgBack(dto.getVehicleImgBack());
        }
        if (dto.getVehicleImgFrontInterior() != null) {
            vehicle.setVehicleImgFrontInterior(dto.getVehicleImgFrontInterior());
        }
        if (dto.getVehicleImgBackInterior() != null) {
            vehicle.setVehicleImgBackInterior(dto.getVehicleImgBackInterior());
        }
        if (dto.getDriverDrivingLicenseFront() != null) {
            vehicle.setDriverDrivingLicenseFront(dto.getDriverDrivingLicenseFront());
        }
        if (dto.getDriverDrivingLicenseBack() != null) {
            vehicle.setDriverDrivingLicenseBack(dto.getDriverDrivingLicenseBack());
        }
        vehicleRepo.save(vehicle);
    }

    @Override
    public void deleteVehicle(String vehicleId) throws NotFoundException {
        Vehicle vehicle = vehicleRepo
                .findById(vehicleId)
                .orElseThrow(() -> new NotFoundException("vehicle id : " + vehicleId + " npt found"));
        vehicleRepo.delete(vehicle);
    }

    @Override
    public List<VehicleDTO> getAll() {
        return vehicleRepo.findAll().stream().map(mapper::tVehicleDto).toList();
    }

    @Override
    public VehicleDTO getVehicle(String vehicleId) throws NotFoundException {
        Vehicle vehicle = vehicleRepo
                .findById(vehicleId)
                .orElseThrow(() -> new NotFoundException("vehicle id : " + vehicleId + " npt found"));
        return mapper.tVehicleDto(vehicle);
    }

}
