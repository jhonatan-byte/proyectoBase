package com.example.base.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {


    private  String status;


    private String message;

    private String data;


    public ResponseDto(String carFoundSuccessfully, CarDto resultDto) {
    }
}
