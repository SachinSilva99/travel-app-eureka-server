package com.sachin.travelservice.repo;

import com.sachin.travelservice.entity.Travel;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TravelRepo extends MongoRepository<Travel, String> {
    Travel findByUserId(String userId);
    Travel findByHotelStaysHotelStayId(String hotelStayId);
    List<Travel> findAllByHotelStaysHotelStayStartDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );
    List<Travel> findAllByHotelStaysHotelStayEndDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );
    List<Travel> findAllByUserId(@NotBlank String userId);

    @Query("{'travelPlacedDate': ?0, 'isCancelled': false}")
    List<Travel> findNonCancelledTravelsOnDate(@Param("travelPlacedDate") LocalDate date);

    @Query("{'travelPlacedDate': { $gte: ?0, $lte: ?1 }, 'isCancelled': false}")
    List<Travel> findNonCancelledTravelsInWeek(
            @Param("startOfWeek") LocalDate startOfWeek,
            @Param("endOfWeek") LocalDate endOfWeek
    );

    @Query("{'travelPlacedDate': { $gte: ?0, $lte: ?1 }, 'isCancelled': false}")
    List<Travel> findNonCancelledTravelsInMonth(
            @Param("startOfMonth") LocalDate startOfMonth,
            @Param("endOfMonth") LocalDate endOfMonth
    );

    @Query("{'travelPlacedDate': { $gte: ?0, $lte: ?1 }, 'isCancelled': false}")
    List<Travel> findNonCancelledTravelsInYear(
            @Param("startOfYear") LocalDate startOfYear,
            @Param("endOfYear") LocalDate endOfYear
    );

}
