package com.sachin.travelservice.dto.enums.hotel;

import com.sachin.travelservice.exception.InValidDataException;

public enum HotelCategory {
    ONE_STAR,
    TWO_STAR,
    THREE_STAR,
    FOUR_STAR,
    FIVE_STAR;

    public static HotelCategory getCategory(String hotelCategory) {
        return switch (hotelCategory) {
            case "ONE_STAR" -> ONE_STAR;
            case "TWO_STAR" -> TWO_STAR;
            case "THREE_STAR" -> THREE_STAR;
            case "FOUR_STAR" -> FOUR_STAR;
            case "FIVE_STAR" -> FIVE_STAR;
            default -> throw new InValidDataException(hotelCategory + " invalid star category");
        };
    }
}