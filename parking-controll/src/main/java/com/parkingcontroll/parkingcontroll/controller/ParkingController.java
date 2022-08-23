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

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id){
        parkingService.delete(id);
        return ResponseEntity.noContent().build();

    }

    @PostMapping
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto){
        var parkingCreate = parkingMapper.toParkingCreate(dto);
        var parking = parkingService.create(parkingCreate);
        var result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingDTO> update(@PathVariable String id, @RequestBody ParkingCreateDTO dto){
        var parkingUpdate = parkingMapper.toParkingCreate(dto);
        var parking = parkingService.update(id, parkingUpdate);
        return ResponseEntity.ok(parkingMapper.toParkingDTO(parking));
    }

    @PostMapping("/{id}/exit")
    public ResponseEntity<ParkingDTO> checkOut(@PathVariable String id) {
        //Verificar se já não está fechado e lançar exceção
        Parking parking = parkingService.checkOut(id);
        return ResponseEntity.ok(parkingMapper.toParkingDTO(parking));
    }

}
