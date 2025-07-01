package com.example.base.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    @NotBlank(message = "name cannot be null or empty")
    private  String name;

    @NotNull
    private Integer rut;

    @NotBlank(message = "address cannot be null or empty")
    private  String address;




    @NotNull(message = "age cannot be null or empty")
    private Integer age;

    @NotNull(message = "age cannot be null or empty")
    private Integer phone;

    public UserDto(String name, Integer rut) {
        this.name = name;
        this.rut = rut;
    }
}
