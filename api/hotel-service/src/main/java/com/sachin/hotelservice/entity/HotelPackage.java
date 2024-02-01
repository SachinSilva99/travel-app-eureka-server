package com.sachin.hotelservice.entity;

import com.sachin.hotelservice.entity.enums.HotelPackageRoomType;
import com.sachin.hotelservice.entity.enums.HotelPackageType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class HotelPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String hotelPackageId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private HotelPackageType hotelPackageType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HotelPackageRoomType hotelPackageRoomType;

    @Column(nullable = false)
    private double hotelPackagePrice;

    @ManyToOne
    private Hotel hotel;
}
