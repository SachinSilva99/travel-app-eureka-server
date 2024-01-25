package com.sachin.hotelservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class HotelImage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String hotelImageId;

    @Column(nullable = false, columnDefinition = "LongText")
    @Lob
    private String hotelImgValue;
    @ManyToOne
    private Hotel hotel;
}
