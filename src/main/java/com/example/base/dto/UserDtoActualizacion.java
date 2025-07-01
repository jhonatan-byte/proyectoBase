package com.example.base.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoActualizacion {



        @NotBlank
        private String address;

        @NotNull
        private Integer phone;





}
