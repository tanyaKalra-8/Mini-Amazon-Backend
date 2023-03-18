package com.ECommerce_Backend.Mini_.Amazon.Repository;

import com.ECommerce_Backend.Mini_.Amazon.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {
}
