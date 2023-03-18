package com.ECommerce_Backend.Mini_.Amazon.Controller;

import com.ECommerce_Backend.Mini_.Amazon.Dto.ProductRequestDto;
import com.ECommerce_Backend.Mini_.Amazon.Dto.ProductResponseDto;
import com.ECommerce_Backend.Mini_.Amazon.Enum.Category;
import com.ECommerce_Backend.Mini_.Amazon.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    public String addProduct(@RequestBody ProductRequestDto productRequestDto){
        productService.addProduct(productRequestDto);
        return "Product added successfully";
    }

    public List<ProductResponseDto> viewAllProductByCategory(@RequestParam Category category){
        return productService.viewAllProductByCategory(category);
    }
}
