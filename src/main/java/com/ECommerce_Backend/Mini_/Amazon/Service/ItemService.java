package com.ECommerce_Backend.Mini_.Amazon.Service;

import com.ECommerce_Backend.Mini_.Amazon.Convertor.ItemConvertor;
import com.ECommerce_Backend.Mini_.Amazon.Dto.ItemResponseDto;
import com.ECommerce_Backend.Mini_.Amazon.Exception.ProductNotPresentException;
import com.ECommerce_Backend.Mini_.Amazon.Model.Item;
import com.ECommerce_Backend.Mini_.Amazon.Model.Product;
import com.ECommerce_Backend.Mini_.Amazon.Repository.ItemRepository;
import com.ECommerce_Backend.Mini_.Amazon.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ProductRepository productRepository;

    public ItemResponseDto viewItem(int productId) throws ProductNotPresentException{

        Product product;
        try{
            product = productRepository.findById(productId).get();
        }
        catch (Exception e){
            throw new ProductNotPresentException("Invalid product Id");
        }

        Item item = Item.builder()
                .requiredQuantity(0)
                .product(product)
                .build();

        product.setItem(item);

        //product is the parent
        productRepository.save(product);

        //prepaare the response Dto
        return ItemConvertor.productToItemResponseDto(product);
    }
}
