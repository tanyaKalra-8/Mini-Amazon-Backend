package com.ECommerce_Backend.Mini_.Amazon.Dto;

import com.ECommerce_Backend.Mini_.Amazon.Enum.Category;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

    private String name;
    private int price;
    private int quantity;
    private Category category;
}
