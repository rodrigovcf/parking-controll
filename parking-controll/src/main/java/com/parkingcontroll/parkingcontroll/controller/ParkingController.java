package com.parkingcontroll.parkingcontroll.controller;

import com.parkingcontroll.parkingcontroll.controller.dto.ParkingCreateDTO;
import com.parkingcontroll.parkingcontroll.controller.dto.ParkingDTO;
import com.parkingcontroll.parkingcontroll.controller.mapper.ParkingMapper;
import com.parkingcontroll.parkingcontroll.model.Parking;
import com.parkingcontroll.parkingcontroll.service.ParkingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@Api(tags = "Parking Controller")
public class ParkingController {
    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;


    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    @ApiOperation("Find all parkings")
    public ResponseEntity<List<ParkingDTO>> findAll(){
        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id){
        Parking parking = (Parking) parkingService.findById(id);
        ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.ok(result);

    }

    @PostMapping
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto){
        var parkingCreate = parkingMapper.toParkingCreate(dto);
        Parking parking = parkingService.create(parkingCreate);
        ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
