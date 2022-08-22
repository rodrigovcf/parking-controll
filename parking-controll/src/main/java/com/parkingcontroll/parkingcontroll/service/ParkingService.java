package com.parkingcontroll.parkingcontroll.service;

import com.parkingcontroll.parkingcontroll.exception.ParkingNotFoundException;
import com.parkingcontroll.parkingcontroll.model.Parking;
import com.parkingcontroll.parkingcontroll.repository.ParkingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static Map<String, Parking> parkingMap = new HashMap<>();

//    private final ParkingRepository parkingRepository;

//    public ParkingService(ParkingRepository parkingRepository) {
//        this.parkingRepository = parkingRepository;
//    }

    public List<Parking> findAll(){
        return parkingMap.values().stream().collect(Collectors.toList());
    }
    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public Parking findById(String id) {
        Parking parking = parkingMap.get(id);
        if(parking == null){
            throw new ParkingNotFoundException(id);

        }
        return parking;
    }

    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(uuid, parkingCreate);
        return parkingCreate;
    }

    public void delete(String id) {
        findById(id);
        parkingMap.remove(id);
    }

    public Parking update(String id, Parking parkingCreate) {
        Parking parking = findById(id);
        parking.setColor(parkingCreate.getColor());
        parkingMap.replace(id, parking);
        return parking;
    }

//    public Parking checkOut(String id) {
//        Parking parking = findById(id);
//        parking.setExitDate(LocalDateTime.now());
//        parking.setBill(ParkingCheckOut.getBill(parking));
//        return parkingRepository.save(parking);
//    }
}
