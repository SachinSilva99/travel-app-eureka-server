package com.sachin.userservice.entity.enums;

import com.sachin.userservice.exception.InValidDataException;

public enum GENDER {
    MALE,
    FEMALE,
    OTHER;
    public static GENDER getGender(String gender) {
        return switch (gender) {
            case "MALE" -> GENDER.MALE;
            case "FEMALE" -> GENDER.FEMALE;
            case "OTHER" -> GENDER.OTHER;
            default -> throw new InValidDataException(gender + " invalid gender");
        };
    }
}
