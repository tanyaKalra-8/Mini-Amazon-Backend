package com.ECommerce_Backend.Mini_.Amazon.Service;

import com.ECommerce_Backend.Mini_.Amazon.Convertor.ProductConvertor;
import com.ECommerce_Backend.Mini_.Amazon.Dto.ProductRequestDto;
import com.ECommerce_Backend.Mini_.Amazon.Dto.ProductResponseDto;
import com.ECommerce_Backend.Mini_.Amazon.Enum.Category;
import com.ECommerce_Backend.Mini_.Amazon.Exception.SellerNotPresentException;
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

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotPresentException{
        //create a product
//        Product product = new Product();
//        product.setProductName(productRequestDto.getName());
//        product.setPrice(productRequestDto.getPrice());
//        product.setQuantity(productRequestDto.getQuantity());
//        product.setProductcategory(productRequestDto.getCategory());
//        product.setProductStatus(productRequestDto.getProductStatus());

        Seller seller;
        try{
            seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
        }
        catch (Exception e){
            throw new SellerNotPresentException("Invalid Seller Id");
        }

        Product product = ProductConvertor.productRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);

        seller.getProducts().add(product);

        //saving the parent class--> child will be saved automatically
        sellerRepository.save(seller);

        return ProductConvertor.productToProductResponseDto(product);
    }

    public List<ProductResponseDto> viewAllProductByCategory(Category category) {
        List<Product> products = productRepository.findAllByProductcategory(category);
        List<ProductResponseDto> productResponse = new ArrayList<>();
        ProductResponseDto productResponseDto;

        for (Product product: products){

            productResponseDto = ProductConvertor.productToProductResponseDto(product);

            productResponse.add(productResponseDto);
        }
        return productResponse;
    }
}
