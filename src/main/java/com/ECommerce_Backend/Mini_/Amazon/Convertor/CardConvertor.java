package com.ECommerce_Backend.Mini_.Amazon.Convertor;

import com.ECommerce_Backend.Mini_.Amazon.Dto.CardDto;
import com.ECommerce_Backend.Mini_.Amazon.Dto.CardRequestDto;
import com.ECommerce_Backend.Mini_.Amazon.Dto.CardResponseDto;
import com.ECommerce_Backend.Mini_.Amazon.Model.Card;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CardConvertor {

    public static Card cardRequestDtoToCard(CardRequestDto cardRequestDto){

        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cvv(cardRequestDto.getCvv())
                .cardType(cardRequestDto.getCardType())
                .build();
    }

    public static CardDto CardToCardDto(Card card){

        return CardDto.builder()
                .cardNo(card.getCardNo())
                .cardType(card.getCardType())
                .build();
    }
}
