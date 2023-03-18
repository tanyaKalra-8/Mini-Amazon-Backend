package com.ECommerce_Backend.Mini_.Amazon.Controller;

import com.ECommerce_Backend.Mini_.Amazon.Dto.SellerRequestDto;
import com.ECommerce_Backend.Mini_.Amazon.Dto.SellerResponseDto;
import com.ECommerce_Backend.Mini_.Amazon.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    public String addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        sellerService.addSeller(sellerRequestDto);
        return "Seller added successfully";
    }

    public List<SellerResponseDto> viewAllSellers(){
        return sellerService.viewAllSellers();
    }
}
