package com.sachin.hotelservice.repo;

import com.sachin.hotelservice.entity.HotelImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelImageRepo extends JpaRepository<HotelImage, String> {
}
