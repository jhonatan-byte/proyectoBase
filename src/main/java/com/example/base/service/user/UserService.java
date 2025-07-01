package com.example.base.service.user;

import com.example.base.configuration.BusinessException;
import com.example.base.dto.ResponseDto;
import com.example.base.dto.UserDto;
import com.example.base.dto.UserDtoActualizacion;

import java.util.List;

public interface UserService {

    boolean createUser (UserDto userDto) throws BusinessException;


    UserDto findByRut(Integer id) throws BusinessException;

    UserDto updateUser(Integer id, UserDtoActualizacion userDtoActualizacion) throws BusinessException;


    ResponseDto deleteUser (Integer rut) throws BusinessException;

    List<UserDto> listUser () throws BusinessException;


    UserDto findByPlate(Integer id) throws BusinessException;
}
