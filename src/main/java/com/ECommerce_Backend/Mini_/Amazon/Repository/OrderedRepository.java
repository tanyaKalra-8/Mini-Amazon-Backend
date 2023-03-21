package com.ECommerce_Backend.Mini_.Amazon.Repository;

import com.ECommerce_Backend.Mini_.Amazon.Model.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedRepository extends JpaRepository<Ordered,Integer> {
}
