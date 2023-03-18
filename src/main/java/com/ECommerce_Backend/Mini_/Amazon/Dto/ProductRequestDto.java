package com.ECommerce_Backend.Mini_.Amazon.Dto;

import com.ECommerce_Backend.Mini_.Amazon.Enum.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //contains both getters and setters and required args consructor
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

    private String name;
    private int price;
    private int quantity;
    private Category category;
    private int sellerId;
}
