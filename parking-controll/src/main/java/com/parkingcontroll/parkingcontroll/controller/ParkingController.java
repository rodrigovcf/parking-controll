package com.parkingcontroll.parkingcontroll.controller;

import com.parkingcontroll.parkingcontroll.controller.dto.ParkingDTO;
import com.parkingcontroll.parkingcontroll.controller.mapper.ParkingMapper;
import com.parkingcontroll.parkingcontroll.model.Parking;
import com.parkingcontroll.parkingcontroll.service.ParkingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {
    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;


    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    public List<ParkingDTO> findAll(){
        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return result;

    }
}
