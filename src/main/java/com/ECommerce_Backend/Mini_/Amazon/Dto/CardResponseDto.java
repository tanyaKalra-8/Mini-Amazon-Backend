package com.ECommerce_Backend.Mini_.Amazon.Dto;

import com.ECommerce_Backend.Mini_.Amazon.Enum.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardResponseDto {

    private String customerName;
    private List<CardDto> cards;
}
