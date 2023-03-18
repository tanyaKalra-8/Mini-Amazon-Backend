package com.ECommerce_Backend.Mini_.Amazon.Service;

import com.ECommerce_Backend.Mini_.Amazon.Dto.SellerRequestDto;
import com.ECommerce_Backend.Mini_.Amazon.Dto.SellerResponseDto;
import com.ECommerce_Backend.Mini_.Amazon.Model.Seller;
import com.ECommerce_Backend.Mini_.Amazon.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public void addSeller(SellerRequestDto sellerRequestDto){

        //seller object
        Seller seller = new Seller();
        seller.setName(sellerRequestDto.getName());
        seller.setEmail(sellerRequestDto.getEmail());
        seller.setMobNo(sellerRequestDto.getMobNo());
        seller.setPanNo(sellerRequestDto.getPanNo());

        sellerRepository.save(seller);
    }

    public List<SellerResponseDto> viewAllSellers() {
        List<Seller> sellers = sellerRepository.findAll();
        List<SellerResponseDto> sellerResponse =  new ArrayList<>();
        SellerResponseDto sellerResponseDto;

        for (Seller seller: sellers){
            sellerResponseDto =  new SellerResponseDto();
            sellerResponseDto.setName(seller.getName());
            sellerResponseDto.setEmail(seller.getEmail());
            sellerResponseDto.setMobNo(seller.getMobNo());
            sellerResponseDto.setPanNo(seller.getPanNo());

            sellerResponse.add(sellerResponseDto);
        }
        return sellerResponse;
    }
}
