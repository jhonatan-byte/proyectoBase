package com.example.base.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "USER_DATA")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public UserEntity( String name, Integer rut ) {
        this.name = name;
        this.rut = rut;

    }

    @Column(name = "name")
    private  String name;

    @Column(name = "rut")
    private Integer rut;


    @Column(name = "address")
    private  String address;

    @Column(name = "age")
    private Integer age;

    @Column(name = "phone")
    private Integer phone;



}
