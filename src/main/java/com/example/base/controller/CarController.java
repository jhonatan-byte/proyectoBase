package com.example.base.controller;

import com.example.base.configuration.BusinessException;
import com.example.base.dto.CarActualizacion;
import com.example.base.dto.CarDto;
import com.example.base.dto.ResponseDto;
import com.example.base.service.car.CarService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j

@RestController
@RequestMapping("car")
@Tag(name = "Test", description = "API de prueba con Swagger")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CarController {


    private final CarService carService;



    

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCar(@RequestBody CarDto car) throws BusinessException {
      return   ResponseEntity.ok(carService.creatCar(car));
    }


    @GetMapping("/searchplate/{placa}")
    public ResponseEntity<CarDto> searchplate(@PathVariable  String placa) throws BusinessException {
        CarDto carDto = carService.findByPlaca(placa);

        return ResponseEntity.ok(carDto);

    }

    @PutMapping("/update/{placa}")
    public ResponseEntity<ResponseDto> updateCar(@PathVariable String placa, @RequestBody CarActualizacion car) {
        ResponseDto responseDto = carService.updateCar(placa , car);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/delete/{placa}")
    public ResponseEntity<ResponseDto> deleteCar(@PathVariable String placa) {
             ResponseDto responseDto = carService.deleteCar(placa);
        return ResponseEntity.ok(responseDto);


    }


}


