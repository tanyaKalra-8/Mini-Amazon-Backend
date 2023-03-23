package com.ECommerce_Backend.Mini_.Amazon.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDto {

    private String name;
    private int age;
    private String email;
    private String mobNo;
}
