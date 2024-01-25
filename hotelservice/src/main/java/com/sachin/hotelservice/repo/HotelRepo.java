package com.sachin.hotelservice.repo;

import com.sachin.hotelservice.entity.Hotel;
import com.sachin.hotelservice.entity.HotelImage;
import com.sachin.hotelservice.entity.enums.HotelCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, String> {
    List<Hotel> findAllByHotelCategory(HotelCategory hotelCategory);

    List<Hotel> findAllByIsHotelCancellationCriteriaFree(Boolean isHotelCancellationCriteria);

    List<Hotel> findAllByIsHotelPetsAllowed(Boolean isHotelPetsAllowed);

    List<Hotel> findAllByHotelCategoryAndIsHotelCancellationCriteriaFreeAndIsHotelPetsAllowed(
            HotelCategory hotelCategory, Boolean isHotelCancellationCriteria, Boolean isHotelPetsAllowed);
}
