package com.example.base.repository;

import com.example.base.entity.CarEntity;
import com.example.base.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {


     Optional<CarEntity> findById(Integer id);

     Optional<CarEntity> findByPlaca(String placa);



}


