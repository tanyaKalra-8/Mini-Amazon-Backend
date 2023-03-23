package com.ECommerce_Backend.Mini_.Amazon.Service;

import com.ECommerce_Backend.Mini_.Amazon.Convertor.CustomerConvertor;
import com.ECommerce_Backend.Mini_.Amazon.Convertor.SellerConvertor;
import com.ECommerce_Backend.Mini_.Amazon.Dto.CustomerRequestDto;
import com.ECommerce_Backend.Mini_.Amazon.Dto.CustomerResponseDto;
import com.ECommerce_Backend.Mini_.Amazon.Dto.SellerMobNoUpdateRequestDto;
import com.ECommerce_Backend.Mini_.Amazon.Dto.SellerResponseDto;
import com.ECommerce_Backend.Mini_.Amazon.Exception.CustomerNotPresentException;
import com.ECommerce_Backend.Mini_.Amazon.Exception.EmailNotPresentException;
import com.ECommerce_Backend.Mini_.Amazon.Exception.SellerNotPresentException;
import com.ECommerce_Backend.Mini_.Amazon.Model.Cart;
import com.ECommerce_Backend.Mini_.Amazon.Model.Customer;
import com.ECommerce_Backend.Mini_.Amazon.Model.Seller;
import com.ECommerce_Backend.Mini_.Amazon.Repository.CustomerRepository;
import org.hibernate.transform.CacheableResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public String addCustomer(CustomerRequestDto customerRequestDto) {

        Customer customer = CustomerConvertor.customerRequestDtoToCustomer(customerRequestDto);

        //getting cart ready
        Cart cart =  new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);

        //setting cart in customer
        customer.setCart(cart);

        //customer is parent of cart so just saving customer will work
        customerRepository.save(customer);
        return "Congrats!! Welcome to Mini_Amazon :)";
    }

    public CustomerResponseDto getCustomerById(int id) throws CustomerNotPresentException{

        Customer customer;
        try {
            customer = customerRepository.findById(id).get();
        }
        catch(Exception e){
            throw new CustomerNotPresentException("Invalid customer Id");
        }

        return CustomerConvertor.customerToCustomerResponseDto(customer);
    }

    public List<CustomerResponseDto>  getAllCustomers(){
        List<CustomerResponseDto> customerResponse = new ArrayList<>();

        for (Customer customer: customerRepository.findAll()){
            customerResponse.add(CustomerConvertor.customerToCustomerResponseDto(customer));
        }
        return customerResponse;
    }
    public CustomerResponseDto getCustomerByEmail(String email) throws EmailNotPresentException {

        Customer customer =  customerRepository.findByEmail(email);

        if (customer == null){
            throw new EmailNotPresentException("Invalid Email");
        }
        return CustomerConvertor.customerToCustomerResponseDto(customer);
    }

    public CustomerResponseDto updateMobNo(String email, String mobNo) throws CustomerNotPresentException {

        Customer customer;
        try{
            customer = customerRepository.findByEmail(email);
        }
        catch (Exception e){
            throw new CustomerNotPresentException("Invalid customer info");
        }

        customer.setMobNo(mobNo);
        customerRepository.save(customer);

        return CustomerConvertor.customerToCustomerResponseDto(customer);
    }

    public void deleteById(int id) {
        customerRepository.deleteById(id);
    }
}
