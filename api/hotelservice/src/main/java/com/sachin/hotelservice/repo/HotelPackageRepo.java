package com.sachin.hotelservice.repo;

import com.sachin.hotelservice.entity.HotelPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelPackageRepo extends JpaRepository<HotelPackage, String> {
}
