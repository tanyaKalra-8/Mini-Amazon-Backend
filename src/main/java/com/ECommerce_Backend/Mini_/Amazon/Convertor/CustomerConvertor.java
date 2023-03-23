package com.ECommerce_Backend.Mini_.Amazon.Convertor;

import com.ECommerce_Backend.Mini_.Amazon.Dto.CustomerRequestDto;
import com.ECommerce_Backend.Mini_.Amazon.Dto.CustomerResponseDto;
import com.ECommerce_Backend.Mini_.Amazon.Model.Customer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerConvertor {

    public static Customer customerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){

        return Customer.builder()
                .name(customerRequestDto.getName())
                .age(customerRequestDto.getAge())
                .email(customerRequestDto.getEmail())
                .mobNo(customerRequestDto.getMobNo())
                .build();
    }

    public static CustomerResponseDto customerToCustomerResponseDto(Customer customer){

        return CustomerResponseDto.builder()
                .name(customer.getName())
                .age(customer.getAge())
                .email(customer.getEmail())
                .mobNo(customer.getMobNo())
                .build();
    }
}
