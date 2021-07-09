package com.fedorov.carwash.repository;

import com.fedorov.carwash.model.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderTypeRepository extends JpaRepository<OrderType, Long> {

    Optional<OrderType> getByDescription(String description);
}
