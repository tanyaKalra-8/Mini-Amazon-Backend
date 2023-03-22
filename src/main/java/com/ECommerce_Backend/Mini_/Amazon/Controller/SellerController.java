package com.ECommerce_Backend.Mini_.Amazon.Controller;

import com.ECommerce_Backend.Mini_.Amazon.Dto.SellerMobNoUpdateRequestDto;
import com.ECommerce_Backend.Mini_.Amazon.Dto.SellerRequestDto;
import com.ECommerce_Backend.Mini_.Amazon.Dto.SellerResponseDto;
import com.ECommerce_Backend.Mini_.Amazon.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public SellerResponseDto GetSellerByPanNo(@RequestParam("pan") String panNo){
        return sellerService.GetSellerByPanNo(panNo);
    }

    @GetMapping("/get_by_name")
    public List<SellerResponseDto> GetSellerByName(@RequestParam("name") String name){
        return sellerService.GetSellerByName(name);
    }

    @PutMapping("update/mob")
    public ResponseEntity updateMobNo(@RequestBody SellerMobNoUpdateRequestDto sellerMobNoUpdateRequestDto){

        try {
            return new ResponseEntity<>(sellerService.updateMobNo(sellerMobNoUpdateRequestDto), HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/all")
    public String deleteAllSellers(){
        return sellerService.deleteAllSeller();
    }

    @DeleteMapping("/delete/name/{name}")
    public String deleteByName(@PathVariable("name") String name){

        try{
            return sellerService.deleteByName(name);
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

    @DeleteMapping("/delete/email")
    public String deleteByEmail(@RequestParam("email") String email){
        try {
            return sellerService.deleteByEmail(email);
        }
        catch (Exception e){
            return e.getMessage();
        }
    }
}
