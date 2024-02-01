package com.sachin.vehicleservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sachin.vehicleservice.entity.enums.VehicleCategory;
import com.sachin.vehicleservice.entity.enums.VehicleFuelType;
import com.sachin.vehicleservice.entity.enums.VehicleTransmission;
import com.sachin.vehicleservice.entity.enums.VehicleType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Vehicle {
    @Id
    private String vehicleId;
    @NotNull
    private String vehicleBrand;
    @NotBlank
    private String vehicleName;
    private VehicleCategory vehicleCategory;
    @NotNull
    private VehicleFuelType vehicleFuelType;
    private double vehicleFuelConsumption;
    private Boolean vehicleIsHybrid;
    private int vehicleNoOfSeats;
    private VehicleType vehicleType;
    private VehicleTransmission vehicleTransmission;
    private String vehicleRemarks;

    @NotNull
    private String vehicleDriverName;

    @NotNull
    private String vehicleDriverContact;

    @NotNull
    private String vehicleMainImage;


    private double feeForOneDay100km;
    private double feeForExtra1km;

    @NotNull
    private String vehicleImgFront;
    @NotNull
    private String vehicleImgBack;
    @NotNull
    private String vehicleImgFrontInterior;
    @NotNull
    private String vehicleImgBackInterior;
    @NotNull
    private String driverDrivingLicenseFront;
    @NotNull
    private String driverDrivingLicenseBack;
}
