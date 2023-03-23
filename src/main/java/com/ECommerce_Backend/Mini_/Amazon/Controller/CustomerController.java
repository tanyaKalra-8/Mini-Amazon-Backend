package com.ECommerce_Backend.Mini_.Amazon.Controller;

import com.ECommerce_Backend.Mini_.Amazon.Dto.CustomerRequestDto;
import com.ECommerce_Backend.Mini_.Amazon.Dto.CustomerResponseDto;
import com.ECommerce_Backend.Mini_.Amazon.Model.Customer;
import com.ECommerce_Backend.Mini_.Amazon.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public String addCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        return customerService.addCustomer(customerRequestDto);
    }

    @GetMapping("/get/customer/id")
    public ResponseEntity getCustomerById(@RequestParam("id") int id){
        try {
            return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/customer/all")
    public List<CustomerResponseDto> getAllCustomer(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/get/customer/email")
    public ResponseEntity getCustomerByEmail(@RequestParam("email") String email){

        try {
            return new ResponseEntity<>(customerService.getCustomerByEmail(email), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/mobNo")
    public ResponseEntity updateMobNo(@RequestParam("email") String email, @RequestParam("mobNo") String mobNo) {

        try {
            return new ResponseEntity<>("Mobile number updated successfully: " + customerService.updateMobNo(email,mobNo), HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/id")
    public String deleteById(@RequestParam("id") int id){
        customerService.deleteById(id);
        return "Customer deleted successfully";
    }
}
