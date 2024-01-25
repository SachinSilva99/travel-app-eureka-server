package com.sachin.travelservice.dto.fulldetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sachin.travelservice.dto.enums.vehicle.VehicleCategory;
import com.sachin.travelservice.dto.enums.vehicle.VehicleFuelType;
import com.sachin.travelservice.dto.enums.vehicle.VehicleTransmission;
import com.sachin.travelservice.dto.enums.vehicle.VehicleType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Valid
public class VehicleDTO {

    private String vehicleId;

    @NotBlank
    private String vehicleBrand;
    @NotBlank
    private String vehicleName;

    @NotNull
    private VehicleCategory vehicleCategory;
    @NotNull
    private VehicleFuelType vehicleFuelType;

    @DecimalMin(value = "1", message = "The vehicle Fuel Consumption more than least 1")
    private double vehicleFuelConsumption;

    @NotNull
    private Boolean vehicleIsHybrid;

    @Min(value = 1, message = "The number of seats must be at least 1")
    private int vehicleNoOfSeats;
    @NotNull
    private VehicleType vehicleType;
    @NotNull
    private VehicleTransmission vehicleTransmission;
    @NotNull
    private String vehicleRemarks;
    @NotNull
    private String vehicleDriverName;
    @NotNull
    private String vehicleDriverContact;

    private double feeForOneDay100km;
    private double feeForExtra1km;

    private String vehicleMainImage;
    private String vehicleImgFront;
    private String vehicleImgBack;
    private String vehicleImgFrontInterior;
    private String vehicleImgBackInterior;
    private String driverDrivingLicenseFront;
    private String driverDrivingLicenseBack;
}
