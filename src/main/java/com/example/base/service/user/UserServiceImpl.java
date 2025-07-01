package com.example.base.service.user;

import com.example.base.configuration.BusinessException;
import com.example.base.dto.ResponseDto;
import com.example.base.dto.UserDto;
import com.example.base.dto.UserDtoActualizacion;
import com.example.base.entity.UserEntity;
import com.example.base.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean createUser(UserDto userDto) throws BusinessException {
        log.info("Inicia creacion de usuario con rut {}",userDto.getRut());

        Optional<UserEntity> userRepositoryByRut= userRepository.findByRut(userDto.getRut());

        if (userRepositoryByRut.isPresent()) {
            log.info("Existe usuario en DB con rut {}",userDto.getRut());
            throw  BusinessException.Type.XXX_409.buildWithDetail("El usuario ya existe con el RUT: " + userDto.getRut());
        }

        UserEntity user = UserEntity.builder()
                .name(userDto.getName())
                .rut(userDto.getRut())
                .address(userDto.getAddress())
                .age(Integer.valueOf(userDto.getAge()))
                .build();
        userRepository.save(user);
        log.info("Usuario creado exitosamente");
        return true;
    }




    @Override
    public UserDto findByRut(Integer id) throws BusinessException {

        Optional<UserEntity> user= userRepository.findByRut(id);

        if (user.isPresent()) {
            UserDto userDto = UserDto.builder()
                    .name(user.get().getName())
                    .rut(user.get().getRut())
                    .address(user.get().getAddress())
                    .age(Integer.valueOf(String.valueOf(user.get().getAge())))
                    .build();

            return   userDto;

        }
        throw  BusinessException.Type.XXX_409.buildWithDetail("El usuario no existe: ");


    }



    @Override
    public UserDto updateUser(Integer id, UserDtoActualizacion userDtoActualizacion)throws BusinessException  {
            Optional<UserEntity> user= userRepository.findByRut(id);

            if (user.isPresent()){
                UserEntity userEntity = user.get();

                userEntity.setAddress(userDtoActualizacion.getAddress());
                userEntity.setPhone(userDtoActualizacion.getPhone());

                userRepository.save(userEntity);
                UserDto userDto = UserDto.builder()
                        .address(userEntity.getAddress())
                        .phone(userEntity.getPhone())
                        .build();

                return userDto;
            }
            throw  BusinessException.Type.XXX_409.buildWithDetail("El usuario no existe: ");




    }

    @Override
    public ResponseDto deleteUser(Integer rut) throws BusinessException {
        Optional<UserEntity> user = userRepository.findByRut(rut);

        if (user.isPresent()){
            userRepository.deleteById(Long.valueOf(user.get().getId()));

            ResponseDto responseDto = ResponseDto.builder()
                    .status("ok")
                    .message("El usuario a sido borrado ")
                    .build();
            return responseDto;
        }
        throw  BusinessException.Type.XXX_409.buildWithDetail("El usuario no existe: ");

    }

    @Override
    public List<UserDto> listUser() throws BusinessException {

       List<UserEntity> list = userRepository.findAll();

       if(list.isEmpty()){
           throw  BusinessException.Type.XXX_204.buildWithDetail("la lista esta vacia");
       }

        return list.stream().map(user -> new UserDto(user.getName(), user.getRut())).collect(Collectors.toList());
    }


    @Override
    public UserDto findByPlate(Integer id) throws BusinessException {
        return null;
    }


}

