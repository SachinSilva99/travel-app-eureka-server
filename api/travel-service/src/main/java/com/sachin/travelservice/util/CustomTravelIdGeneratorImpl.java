package com.sachin.travelservice.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Component
public class CustomTravelIdGeneratorImpl implements CustomTravelIdGenerator{
    @Override
    public String generateId(String customerId, LocalDate date) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder customTravelId = new StringBuilder(PREFIX);
        customTravelId.append(customerId);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyMMdd");
        customTravelId.append(date.format(dateFormatter));
        Random random = new Random();
        for (int i = 0; i < LENGTH; i++) {
            int index = random.nextInt(characters.length());
            customTravelId.append(characters.charAt(index));
        }
        return customTravelId.toString();
    }
}
