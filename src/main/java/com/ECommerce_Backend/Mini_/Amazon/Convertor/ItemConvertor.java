package com.ECommerce_Backend.Mini_.Amazon.Convertor;

import com.ECommerce_Backend.Mini_.Amazon.Dto.ItemResponseDto;
import com.ECommerce_Backend.Mini_.Amazon.Dto.ProductResponseDto;
import com.ECommerce_Backend.Mini_.Amazon.Model.Item;
import com.ECommerce_Backend.Mini_.Amazon.Model.Product;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ItemConvertor {

    public static ItemResponseDto productToItemResponseDto(Product product){

        return ItemResponseDto.builder()
                .productName(product.getProductName())
                .price(product.getPrice())
                .productCategory(product.getProductcategory())
                .productStatus(product.getProductStatus())
                .build();
    }
}
