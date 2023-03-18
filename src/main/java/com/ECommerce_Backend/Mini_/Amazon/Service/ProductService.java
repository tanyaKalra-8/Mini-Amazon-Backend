package com.ECommerce_Backend.Mini_.Amazon.Service;

import com.ECommerce_Backend.Mini_.Amazon.Dto.ProductRequestDto;
import com.ECommerce_Backend.Mini_.Amazon.Dto.ProductResponseDto;
import com.ECommerce_Backend.Mini_.Amazon.Enum.Category;
import com.ECommerce_Backend.Mini_.Amazon.Model.Product;
import com.ECommerce_Backend.Mini_.Amazon.Model.Seller;
import com.ECommerce_Backend.Mini_.Amazon.Repository.ProductRepository;
import com.ECommerce_Backend.Mini_.Amazon.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    public void addProduct(ProductRequestDto productRequestDto) {
        //create a product
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        product.setQuantity(productRequestDto.getQuantity());
        product.setCategory(productRequestDto.getCategory());

        //finding the seller
        Seller seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
        seller.getProducts().add(product);
        product.setSeller(seller);

        sellerRepository.save(seller);
    }

    public List<ProductResponseDto> viewAllProductByCategory(Category category) {
        List<Product> products = productRepository.findByCategory(category);
        List<ProductResponseDto> productResponse = new ArrayList<>();
        ProductResponseDto productResponseDto;

        for (Product product: products){

            productResponseDto = new ProductResponseDto();
            productResponseDto.setName(product.getName());
            productResponseDto.setPrice(product.getPrice());
            productResponseDto.setQuantity(product.getQuantity());
            productResponseDto.setCategory(product.getCategory());

            productResponse.add(productResponseDto);
        }
        return productResponse;
    }
}
