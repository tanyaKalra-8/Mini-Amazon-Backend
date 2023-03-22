package com.ECommerce_Backend.Mini_.Amazon.Service;

import com.ECommerce_Backend.Mini_.Amazon.Convertor.SellerConvertor;
import com.ECommerce_Backend.Mini_.Amazon.Dto.SellerMobNoUpdateRequestDto;
import com.ECommerce_Backend.Mini_.Amazon.Dto.SellerRequestDto;
import com.ECommerce_Backend.Mini_.Amazon.Dto.SellerResponseDto;
import com.ECommerce_Backend.Mini_.Amazon.Exception.SellerNotPresentException;
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

    public String addSeller(SellerRequestDto sellerRequestDto){

        //seller object
//        Seller seller = new Seller();
//        seller.setName(sellerRequestDto.getName());
//        seller.setEmail(sellerRequestDto.getEmail());
//        seller.setMobNo(sellerRequestDto.getMobNo());
//        seller.setPanNo(sellerRequestDto.getPanNo());

        //Builder --> annotation - different way to create an object
        //written in seller class
        //professional way of building an object
        //created convertor package so that code looks clean
        Seller seller = SellerConvertor.sellerRequestDtoToSeller(sellerRequestDto);

        sellerRepository.save(seller);
        return "Welcome! Now you can sell on Mini-Amazon. Your ID is: " + seller.getId();
    }

    public List<SellerResponseDto> viewAllSellers() {
        List<Seller> sellers = sellerRepository.findAll();
        List<SellerResponseDto> sellerResponse =  new ArrayList<>();
        SellerResponseDto sellerResponseDto;

        for (Seller seller: sellers){
            sellerResponseDto =  SellerConvertor.sellerToSellerResponseDto(seller);

            sellerResponse.add(sellerResponseDto);
        }
        return sellerResponse;
    }

    public SellerResponseDto GetSellerByPanNo(String panNo){
        Seller seller = sellerRepository.findBypanNo(panNo);

        return SellerConvertor.sellerToSellerResponseDto(seller);
    }

    public List<SellerResponseDto> GetSellerByName(String name){
        List<Seller> sellerList = sellerRepository.findAllByName(name);
        List<SellerResponseDto> sellerResponse = new ArrayList<>();

        for (Seller seller: sellerList){
            sellerResponse.add(SellerConvertor.sellerToSellerResponseDto(seller));
        }
        return sellerResponse;
    }

    public String deleteAllSeller() {
        sellerRepository.deleteAll();
        return "Sellers deleted successfully";
    }

    public String deleteByName(String name) throws SellerNotPresentException{
        List<Seller> sellers = sellerRepository.findAllByName(name);

        if (sellers==null){
            throw new SellerNotPresentException("Invalid Seller info");
        }

        for (Seller seller: sellers) {
            sellerRepository.deleteById(seller.getId());
        }
        return "sellers deleted successfully";
    }

    public String deleteByEmail(String email) throws SellerNotPresentException{
        Seller seller = sellerRepository.findByEmail(email);

        if (seller==null){
            throw new SellerNotPresentException("Invalid Seller info");
        }

        sellerRepository.deleteById(seller.getId());

        return "seller deleted successfully";
    }

    public SellerResponseDto updateMobNo(SellerMobNoUpdateRequestDto sellerMobNoUpdateRequestDto) throws SellerNotPresentException{

        Seller seller;
        try{
            seller = sellerRepository.findBypanNo(sellerMobNoUpdateRequestDto.getPanNo());
        }
        catch (Exception e){
            throw new SellerNotPresentException("Invalid Seller info");
        }

        seller.setMobNo(sellerMobNoUpdateRequestDto.getNewMobNo());
        sellerRepository.save(seller);

        return SellerConvertor.sellerToSellerResponseDto(seller);
    }
}
