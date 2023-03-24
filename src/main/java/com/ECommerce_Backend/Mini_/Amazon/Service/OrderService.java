package com.ECommerce_Backend.Mini_.Amazon.Service;

import com.ECommerce_Backend.Mini_.Amazon.Convertor.ItemConvertor;
import com.ECommerce_Backend.Mini_.Amazon.Dto.ItemResponseDto;
import com.ECommerce_Backend.Mini_.Amazon.Dto.OrderRequestDto;
import com.ECommerce_Backend.Mini_.Amazon.Dto.OrderResponseDto;
import com.ECommerce_Backend.Mini_.Amazon.Enum.ProductStatus;
import com.ECommerce_Backend.Mini_.Amazon.Exception.CardNotPresentException;
import com.ECommerce_Backend.Mini_.Amazon.Exception.CustomerNotPresentException;
import com.ECommerce_Backend.Mini_.Amazon.Exception.InsufficientQuantityException;
import com.ECommerce_Backend.Mini_.Amazon.Exception.ProductNotPresentException;
import com.ECommerce_Backend.Mini_.Amazon.Model.*;
import com.ECommerce_Backend.Mini_.Amazon.Repository.CardRepository;
import com.ECommerce_Backend.Mini_.Amazon.Repository.CustomerRepository;
import com.ECommerce_Backend.Mini_.Amazon.Repository.OrderedRepository;
import com.ECommerce_Backend.Mini_.Amazon.Repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {

    @Autowired
    OrderedRepository orderedRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

//    @Autowired
//    JavaMailSender emailSender;

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws ProductNotPresentException, CustomerNotPresentException, InsufficientQuantityException {

        Customer customer;
        try{
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerNotPresentException("Invalid customer Id");
        }

        Product product;
        try{
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        }
        catch (Exception e){
            throw new ProductNotPresentException("Invalid product Id");
        }

        //product and customer both exists
        //I have to check if quantity is valid

        if (product.getQuantity() < orderRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("Sorry!! Required Quantity is not available.");
        }

        Ordered order = new Ordered();
        order.setTotalCost(orderRequestDto.getRequiredQuantity()* product.getPrice());
        order.setDeliveryCharge(40);
        Card card = customer.getCards().get(0);
        String cardNo = "";
        for(int i=0;i<card.getCardNo().length()-4;i++)
            cardNo += 'X';
        cardNo += card.getCardNo().substring(card.getCardNo().length()-4);
        order.setCardUsedForPayment(cardNo);

        Item item = new Item();
        item.setRequiredQuantity(orderRequestDto.getRequiredQuantity());
        item.setProduct(product);
        item.setOrder(order);
        order.getItems().add(item);
        order.setCustomer(customer);

        int leftQuantity = product.getQuantity()-orderRequestDto.getRequiredQuantity();
        if(leftQuantity<=0)
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        product.setQuantity(leftQuantity);

        customer.getOrders().add(order);
        Customer savedCustomer = customerRepository.save(customer);
        Ordered savedOrder = savedCustomer.getOrders().get(savedCustomer.getOrders().size()-1);

        //prepare response DTO
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .productName(product.getProductName())
                .orderDate(savedOrder.getOrderDate())
                .quantityOrdered(orderRequestDto.getRequiredQuantity())
                .cardUsedForPayment(cardNo)
                .itemPrice(product.getPrice())
                .totalCost(order.getTotalCost())
                .deliveryCharge(40)
                .build();

        // send an email
//        String text = "Congrats your order with total value "+order.getTotalCost()+" has been placed";
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(/*email id*/ "");
//        message.setTo(customer.getEmail());
//        message.setSubject("Order Placed Notification");
//        message.setText(text);
//        emailSender.send(message);

        return orderResponseDto;
    }
}
