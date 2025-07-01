package com.example.base.service.car;

import com.example.base.configuration.BusinessException;
import com.example.base.dto.CarActualizacion;
import com.example.base.dto.CarDto;
import com.example.base.dto.ResponseDto;
import com.example.base.dto.UserDto;
import com.example.base.entity.CarEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CarService {

    CarDto findByPlaca(String placa) throws BusinessException;

    ResponseDto creatCar(CarDto car) throws BusinessException;

    ResponseDto deleteCar(String placa);


    ResponseDto updateCar(String placa , CarActualizacion carActualizacion );
}
