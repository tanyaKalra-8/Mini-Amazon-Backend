package com.ECommerce_Backend.Mini_.Amazon.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerRequestDto {

    private String name;
    private String email;
    private String mobNo;
    private String panNo;
}
