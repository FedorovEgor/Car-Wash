package com.fedorov.carwash.repository;

import com.fedorov.carwash.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByDate(Date date);

    @Modifying
    @Query("select o from Order o where o.user.id = ?1 and o.date = ?2")
    List<Order> findAllByUserIdAndDate(Long userId, Date date);
}
