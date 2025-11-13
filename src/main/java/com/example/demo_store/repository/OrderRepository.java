package com.example.demo_store.repository;

import com.example.demo_store.dto.RevenueDTO;
import com.example.demo_store.entity.Order;
import com.example.demo_store.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT DAY(o.order_date) AS day, SUM(oi.price * oi.quantity) AS totalRevenue " +
            "FROM orders o " +
            "JOIN order_item oi ON o.id = oi.order_id " +
            "WHERE MONTH(o.order_date) = :month AND YEAR(o.order_date) = :year " +
            "GROUP BY DAY(o.order_date) " +
            "ORDER BY DAY(o.order_date)", nativeQuery = true)
    List<Object[]> getMonthlyRevenueNative(@Param("month") int month, @Param("year") int year);

    List<Order> findByUser(User user);

}
