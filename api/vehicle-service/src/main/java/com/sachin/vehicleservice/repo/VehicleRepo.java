package com.sachin.vehicleservice.repo;

import com.sachin.vehicleservice.entity.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepo extends MongoRepository<Vehicle,String> {
}
