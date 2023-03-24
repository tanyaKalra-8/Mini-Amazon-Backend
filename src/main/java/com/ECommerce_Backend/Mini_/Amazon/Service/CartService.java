package com.ECommerce_Backend.Mini_.Amazon.Service;

import com.ECommerce_Backend.Mini_.Amazon.Dto.OrderRequestDto;
import com.ECommerce_Backend.Mini_.Amazon.Dto.OrderResponseDto;
import com.ECommerce_Backend.Mini_.Amazon.Enum.ProductStatus;
import com.ECommerce_Backend.Mini_.Amazon.Exception.CustomerNotPresentException;
import com.ECommerce_Backend.Mini_.Amazon.Exception.InsufficientQuantityException;
import com.ECommerce_Backend.Mini_.Amazon.Exception.ProductNotPresentException;
import com.ECommerce_Backend.Mini_.Amazon.Model.*;
import com.ECommerce_Backend.Mini_.Amazon.Repository.CardRepository;
import com.ECommerce_Backend.Mini_.Amazon.Repository.CustomerRepository;
import com.ECommerce_Backend.Mini_.Amazon.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    public String addToCart(OrderRequestDto orderRequestDto) throws ProductNotPresentException, CustomerNotPresentException, InsufficientQuantityException{

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

        Cart cart = customer.getCart();

        int newCost = cart.getCartTotal() + orderRequestDto.getRequiredQuantity()* product.getPrice();
        cart.setCartTotal(newCost);

        //make a new item and add to cart
        Item item = new Item();
        item.setRequiredQuantity(orderRequestDto.getRequiredQuantity());
        item.setCart(cart);
        item.setProduct(product);

        cart.getItems().add(item);

        customerRepository.save(customer);

        return "Item has been added to your cart!!";
    }

    public List<OrderResponseDto> checkoutCart(int customerId) throws ProductNotPresentException, CustomerNotPresentException, InsufficientQuantityException{

        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }
        catch (Exception e){
            throw new CustomerNotPresentException("Invalid customer Id");
        }

        List<OrderResponseDto> orderResponseDtos = new ArrayList<>();
        Cart cart = customer.getCart();

        for (Item item: cart.getItems()){

            Ordered order = new Ordered();
            order.setTotalCost(item.getRequiredQuantity()* item.getProduct().getPrice());
            order.setDeliveryCharge(0);
            order.setCustomer(customer);
            order.getItems().add(item);

            Card card = customer.getCards().get(0);
            String cardNo = "";
            for(int i=0;i<card.getCardNo().length()-4;i++)
                cardNo += 'X';
            cardNo += card.getCardNo().substring(card.getCardNo().length()-4);
            order.setCardUsedForPayment(cardNo);
            order.setId(order.getId());

            int leftQuantity = item.getProduct().getQuantity()-item.getRequiredQuantity();
            if(leftQuantity<=0)
                item.getProduct().setProductStatus(ProductStatus.OUT_OF_STOCK);
            item.getProduct().setQuantity(leftQuantity);

            customer.getOrders().add(order);

            //prepare response DTO
            OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                    .productName(item.getProduct().getProductName())
                    .orderDate(order.getOrderDate())
                    .quantityOrdered(item.getRequiredQuantity())
                    .cardUsedForPayment(cardNo)
                    .itemPrice(item.getProduct().getPrice())
                    .totalCost(order.getTotalCost())
                    .deliveryCharge(40)
                    .build();

            orderResponseDtos.add(orderResponseDto);
        }

        //empty the cart
        cart.setItems(new ArrayList<>());
        cart.setCartTotal(0);

        //saving the parent
        customerRepository.save(customer);


        return orderResponseDtos;
    }
}
