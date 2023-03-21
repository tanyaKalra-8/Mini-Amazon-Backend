package com.ECommerce_Backend.Mini_.Amazon.Controller;

import com.ECommerce_Backend.Mini_.Amazon.Dto.SellerRequestDto;
import com.ECommerce_Backend.Mini_.Amazon.Dto.SellerResponseDto;
import com.ECommerce_Backend.Mini_.Amazon.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public String addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        return sellerService.addSeller(sellerRequestDto);
    }

    @GetMapping("/get")
    public List<SellerResponseDto> viewAllSellers(){
        return sellerService.viewAllSellers();
    }

    @GetMapping("/get_by_pan")
    public SellerResponseDto GetSellerByPanNo(@RequestParam String panNo){
        return sellerService.GetSellerByPanNo(panNo);
    }

    @GetMapping("/get_by_name")
    public List<SellerResponseDto> GetSellerByName(@RequestParam String name){
        return sellerService.GetSellerByName(name);
    }
}
