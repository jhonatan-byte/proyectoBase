package com.example.base.repository;

import com.example.base.entity.CarEntity;
import com.example.base.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

     Optional<UserEntity> findByRut(Integer rut);




    }


