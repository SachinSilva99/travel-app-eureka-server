package com.sachin.travelservice.repo;

import com.sachin.travelservice.entity.HotelStay;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelStayRepo extends MongoRepository<HotelStay, String> {
}
