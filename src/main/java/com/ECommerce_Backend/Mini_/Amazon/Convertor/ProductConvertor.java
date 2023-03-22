package com.ECommerce_Backend.Mini_.Amazon.Convertor;

import com.ECommerce_Backend.Mini_.Amazon.Dto.ProductRequestDto;
import com.ECommerce_Backend.Mini_.Amazon.Dto.ProductResponseDto;
import com.ECommerce_Backend.Mini_.Amazon.Enum.ProductStatus;
import com.ECommerce_Backend.Mini_.Amazon.Model.Product;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductConvertor {

    public static Product productRequestDtoToProduct(ProductRequestDto productRequestDto){

        return Product.builder()
                .productName(productRequestDto.getName())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .productcategory(productRequestDto.getCategory())
                .productStatus(ProductStatus.AVAILABLE)
                .build();

    }

    public static ProductResponseDto productToProductResponseDto(Product product){

        return ProductResponseDto.builder()
                .name(product.getProductName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .category(product.getProductcategory())
                .productStatus(product.getProductStatus())
                .build();
    }
}
