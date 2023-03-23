package com.ECommerce_Backend.Mini_.Amazon.Service;

import com.ECommerce_Backend.Mini_.Amazon.Convertor.CardConvertor;
import com.ECommerce_Backend.Mini_.Amazon.Dto.CardDto;
import com.ECommerce_Backend.Mini_.Amazon.Dto.CardRequestDto;
import com.ECommerce_Backend.Mini_.Amazon.Dto.CardResponseDto;
import com.ECommerce_Backend.Mini_.Amazon.Exception.CardNotPresentException;
import com.ECommerce_Backend.Mini_.Amazon.Exception.CustomerNotPresentException;
import com.ECommerce_Backend.Mini_.Amazon.Model.Card;
import com.ECommerce_Backend.Mini_.Amazon.Model.Customer;
import com.ECommerce_Backend.Mini_.Amazon.Repository.CardRepository;
import com.ECommerce_Backend.Mini_.Amazon.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CustomerRepository customerRepository;


    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotPresentException{

        Customer customer;
        try {
            customer = customerRepository.findById(cardRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerNotPresentException("Invalid customer Id");
        }

        Card card = new Card();
        card = CardConvertor.cardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);

        customer.getCards().add(card);
        //saving the parent entity
        customerRepository.save(customer);

        //preparing the return entity
        List<CardDto> cardDtos =  new ArrayList<>();

        //fetching cardDto details
        for (Card card1: customer.getCards()){
            cardDtos.add(CardConvertor.CardToCardDto(card1));
        }

        //setting card values
        CardResponseDto cardResponseDto = new CardResponseDto();
        cardResponseDto.setCustomerName(customer.getName());
        cardResponseDto.setCards(cardDtos);

        return cardResponseDto;
    }

    public List<CardDto> allCardsById(int id) throws CustomerNotPresentException{

        Customer customer;
        try{
            customer = customerRepository.findById(id).get();
        }
        catch (Exception e){
            throw new CustomerNotPresentException("Invalid customer Id");
        }

        List<CardDto> cardResponseDtos = new ArrayList<>();

        for (Card card: customer.getCards()){
            cardResponseDtos.add(CardConvertor.CardToCardDto(card));
        }
        return cardResponseDtos;
    }

    public String deleteCardByCardNo(String cardNo) throws CardNotPresentException {

        Card card;
        card = cardRepository.findByCardNo(cardNo);

        if (card==null){
            throw new CardNotPresentException("Invalid Card Number");
        }

        cardRepository.deleteById(card.getId());
        return "Card deleted successfully";
    }

}
