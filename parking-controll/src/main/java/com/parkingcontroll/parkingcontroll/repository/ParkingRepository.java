package com.parkingcontroll.parkingcontroll.repository;

import com.parkingcontroll.parkingcontroll.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, String> {
}