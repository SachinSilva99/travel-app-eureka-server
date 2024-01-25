package com.sachin.userservice.entity.enums;

import com.sachin.userservice.exception.InValidDataException;

public enum USER_TYPE {
    USER_ADMIN,
    VEHICLE_ADMIN,
    GUIDE_ADMIN,
    HOTEL_ADMIN,
    TRAVEL_ADMIN,
    REGISTERED_USER;
    public static USER_TYPE getUserType(String userType) {
        return switch (userType) {
            case "USER_ADMIN" -> USER_TYPE.USER_ADMIN;
            case "VEHICLE_ADMIN" -> USER_TYPE.VEHICLE_ADMIN;
            case "TRAVEL_ADMIN" -> USER_TYPE.TRAVEL_ADMIN;
            case "GUIDE_ADMIN" -> USER_TYPE.GUIDE_ADMIN;
            case "HOTEL_ADMIN" -> USER_TYPE.HOTEL_ADMIN;
            case "REGISTERED_USER" -> USER_TYPE.REGISTERED_USER;
            default -> throw new InValidDataException(userType + " invalid user type");
        };
    }
}
