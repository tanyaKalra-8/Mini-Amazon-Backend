package com.ECommerce_Backend.Mini_.Amazon.Repository;

import com.ECommerce_Backend.Mini_.Amazon.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {

    Seller findBypanNo(String panNo);

    List<Seller> findByName(String name);
}
