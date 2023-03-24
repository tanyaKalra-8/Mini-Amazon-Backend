package com.ECommerce_Backend.Mini_.Amazon.Controller;

import com.ECommerce_Backend.Mini_.Amazon.Dto.OrderRequestDto;
import com.ECommerce_Backend.Mini_.Amazon.Dto.OrderResponseDto;
import com.ECommerce_Backend.Mini_.Amazon.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public String addToCart(@RequestBody OrderRequestDto orderRequestDto){

        try{
            return cartService.addToCart(orderRequestDto);
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

    @PostMapping("/checkout/{customerId}")
    public ResponseEntity checkoutCart(@PathVariable("customerId") int customerId){

        List<OrderResponseDto> orderResponseDtos;
        try{
            orderResponseDtos = cartService.checkoutCart(customerId);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(orderResponseDtos, HttpStatus.ACCEPTED);
    }
}
