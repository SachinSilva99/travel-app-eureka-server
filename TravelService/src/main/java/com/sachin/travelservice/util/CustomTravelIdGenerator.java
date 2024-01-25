package com.sachin.travelservice.util;

import java.time.LocalDate;

public interface CustomTravelIdGenerator {
     String PREFIX = "NEXT";
     int LENGTH = 3;
    String generateId(String customerId, LocalDate date);
}
