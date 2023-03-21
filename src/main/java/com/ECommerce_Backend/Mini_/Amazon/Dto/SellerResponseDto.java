package com.ECommerce_Backend.Mini_.Amazon.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerResponseDto {

    private String name;
    private String email;
    private String mobNo;
    private String panNo;

}
