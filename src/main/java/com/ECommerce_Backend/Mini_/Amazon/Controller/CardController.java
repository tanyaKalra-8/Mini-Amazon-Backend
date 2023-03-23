package com.ECommerce_Backend.Mini_.Amazon.Controller;

import com.ECommerce_Backend.Mini_.Amazon.Dto.CardDto;
import com.ECommerce_Backend.Mini_.Amazon.Dto.CardRequestDto;
import com.ECommerce_Backend.Mini_.Amazon.Dto.CardResponseDto;
import com.ECommerce_Backend.Mini_.Amazon.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto){
        try {
            return new ResponseEntity<>(cardService.addCard(cardRequestDto), HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/id")
    public ResponseEntity allCardsById(@RequestParam("id") int id){

        try {
            return new ResponseEntity<>(cardService.allCardsById(id), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete")
    public String deleteByCardNo(@RequestParam("cardNo") String cardNo){

        try{
            return cardService.deleteCardByCardNo(cardNo);
        }
        catch (Exception e){
            return e.getMessage();
        }
    }
}
