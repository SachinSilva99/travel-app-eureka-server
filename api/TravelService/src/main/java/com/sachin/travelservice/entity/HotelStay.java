package com.sachin.travelservice.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class HotelStay {

    @Id
    private String hotelStayId;
    private int hotelStayOrderNumber;
    @Field("travelId")
    private String travelId;

    @NotEmpty
    private LocalDate hotelStayStartDate;

    @NotEmpty
    private LocalDate hotelStayEndDate;

    @NotEmpty
    private double hotelStayTotalCost;
    @NotBlank
    private String location;

    private double lat;
    private double lng;

    @NotBlank
    private String hotelStayHotelId;

    @NotEmpty
    private String hotelStayHotelPackageId;
}
