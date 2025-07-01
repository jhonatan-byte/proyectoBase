package com.example.base.controller;

import com.example.base.configuration.BusinessException;
import com.example.base.dto.ResponseDto;
import com.example.base.dto.UserDto;
import com.example.base.dto.UserDtoActualizacion;
import com.example.base.repository.UserRepository;
import com.example.base.service.user.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j

@RestController
@RequestMapping("user")
@Tag(name = "Test", description = "API de prueba con Swagger")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;

    @PostMapping("/create")
    @Operation(summary = "Devuelve un saludo", description = "Endpoint de prueba")
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody UserDto userDto) throws BusinessException {

        userService.createUser(userDto);

        ResponseDto responseDto = ResponseDto.builder()
                .message("Usuario creado con exito")
                .status("OK")
                .build();
        log.info("usuario creado");
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/find/{rut}")
    public ResponseEntity<UserDto> findByPlate(@PathVariable("id") Integer rut) throws BusinessException {
        UserDto userDto = userService.findByRut(rut);
        log.info("se a encontrado el usuario");
        return ResponseEntity.ok(userDto);


    }

    @PutMapping("atualizer/{rut}")
    public ResponseEntity<UserDto> actulizer(@PathVariable("irut") Integer rut,
                                             @RequestBody UserDtoActualizacion userDtoActualizacion) throws BusinessException {

        UserDto userDto = userService.updateUser(rut, userDtoActualizacion);
        return  ResponseEntity.ok(userDto);

    }


    @DeleteMapping("delete/{rut}")
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable Integer rut) throws BusinessException {
        ResponseDto responseDto = userService.deleteUser(rut);

        return ResponseEntity.ok(responseDto);

    }

}


