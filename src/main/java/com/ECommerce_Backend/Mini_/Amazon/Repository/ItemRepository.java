package com.ECommerce_Backend.Mini_.Amazon.Repository;

import com.ECommerce_Backend.Mini_.Amazon.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
}
