package com.ECommerce_Backend.Mini_.Amazon.Controller;

import com.ECommerce_Backend.Mini_.Amazon.Dto.ProductRequestDto;
import com.ECommerce_Backend.Mini_.Amazon.Dto.ProductResponseDto;
import com.ECommerce_Backend.Mini_.Amazon.Enum.Category;
import com.ECommerce_Backend.Mini_.Amazon.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public String addProduct(@RequestBody ProductRequestDto productRequestDto){
        productService.addProduct(productRequestDto);
        return "Product added successfully";
    }

    @GetMapping("/view_by_category")
    public List<ProductResponseDto> viewAllProductByCategory(@RequestParam Category category){
        return productService.viewAllProductByCategory(category);
    }
}
