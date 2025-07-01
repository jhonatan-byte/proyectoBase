package com.example.base.service.car;

import com.example.base.configuration.BusinessException;
import com.example.base.dto.CarActualizacion;
import com.example.base.dto.CarDto;
import com.example.base.dto.ResponseDto;
import com.example.base.entity.CarEntity;
import com.example.base.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;



@Slf4j
@Service
public class CarServiceImpl implements CarService {

    private final CarRepository repository;

    public CarServiceImpl(CarRepository repository) {
        this.repository = repository;
    }


    @Override
    public ResponseDto creatCar(CarDto car) throws BusinessException {

        Optional<CarEntity> dto = repository.findByPlaca(car.getPlate());
        if (dto.isPresent()) {
            log.info("El auto ya existe en base de datos");
            throw BusinessException.Type.XXX_409.buildWithDetail("El auto con placa {} ya existe: " + car.getPlate());
        }

        CarEntity carEntity = CarEntity.builder()
                .modelo(car.getModel())
                .marca(car.getMark())
                .placa(car.getPlate())
                .color(car.getColor())
                .build();

        repository.save(carEntity);
        log.info("Auto creado");

        return ResponseDto.builder().message("carro creado").status("ok").data(String.valueOf(carEntity)).build();


    }



    @Override
    public CarDto findByPlaca(String placa) throws BusinessException {
        log.info("Buscado informacion de auto con placa {}", placa);

        Optional<CarEntity> car = repository.findByPlaca(placa);

        if (car.isPresent()) {
            log.info("Se encontro informacion de auto ");
            CarDto carDto = CarDto.builder()
                    .mark(car.get().getMarca())
                    .model(car.get().getModelo())
                    .plate(car.get().getPlaca())
                    .color(String.valueOf(Integer.valueOf(String.valueOf(car.get().getColor())))).build();

            return carDto;

        }

        throw BusinessException.Type.autoNotFound("Auto no existe");

    }


    @Override
    public ResponseDto updateCar(String placa, CarActualizacion carActualizacion) {
        log.info("inicio de buequeda");
        Optional<CarEntity> car = repository.findByPlaca(placa);

        if (car.isPresent()) {

            log.info("carro encontrado, inicia la actualizacion");

            CarEntity updateCar = car.get();
            updateCar.setColor(carActualizacion.getColor());
            updateCar.setMarca(carActualizacion.getMark());
            updateCar.setModelo(carActualizacion.getModel());


            repository.save(updateCar);

            ResponseDto responseDto = ResponseDto.builder()
                    .message("El carro fue actualizado exitosamente")
                    .status("ok")
                    .data("...")
                    .build();

            log.info("carro actualizado");

            return responseDto;

        }
        ResponseDto responseDto = ResponseDto.builder()
                .message("El carro no fue encontrado")
                .build();
        log.info("carro no fue encontrado");

        return responseDto;
    }

    @Override
    public ResponseDto deleteCar(String placa) {

        log.info("inicio de busqueda");

        Optional<CarEntity> car = repository.findByPlaca(placa);

        if (car.isPresent()){



            log.info("carro encontrado , inicio de barrado");
            repository.delete(car.get());

            ResponseDto responseDto = ResponseDto.builder()
                    .status("ok")
                    .message("El carro ha sido borrado ")
                    .build();
            return responseDto;
        }
        ResponseDto responseDto = ResponseDto.builder()
                .message("El carro no fue encontrado")
                .build();
        log.info("carro no fue encontrado");

        log.info("prueba");
        return responseDto;
    }

}
