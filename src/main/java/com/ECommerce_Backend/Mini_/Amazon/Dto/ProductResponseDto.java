package com.ECommerce_Backend.Mini_.Amazon.Dto;

import com.ECommerce_Backend.Mini_.Amazon.Enum.Category;
import com.ECommerce_Backend.Mini_.Amazon.Enum.ProductStatus;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {

    private String name;
    private int price;
    private int quantity;
    private Category category;
    private ProductStatus productStatus;
}
