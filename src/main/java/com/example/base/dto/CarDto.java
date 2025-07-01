package com.example.base.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDto {

//    @NonNull(message = "mark cannot be null or empty")
    private String model;


//    @NotBlank(message ="plate cannot be null or empty" )
    private String plate;

//    @NotBlank(message = "mark cannot be null or empty")
    private String mark;

    private String color;



}
