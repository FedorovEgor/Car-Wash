package com.fedorov.carwash.service;

import com.fedorov.carwash.model.Order;
import com.fedorov.carwash.model.User;
import com.fedorov.carwash.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private UserService userService;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    public List<Order> getAllCurrentlyLoggedUsersOrders() {
        User loggedUser = userService.getCurrentlyLoggedUser();
        List<Order> currentlyLoggedUserOrders = loggedUser.getOrders();

        if (currentlyLoggedUserOrders == null) {
            throw new EntityNotFoundException("No orders found");
        }

        return currentlyLoggedUserOrders;
    }

    public void insertOrder(Order order) {
        User loggedUser = userService.getCurrentlyLoggedUser();

        order.setDate(order.getDate());
        order.setUser(loggedUser);
        order.setOrderType(order.getOrderType());
        orderRepository.save(order);
    }


    public Order getOrderById(Long id) {
        return orderRepository.getById(id);
    }

    public boolean updateOrder(Order order) {
        if (orderRepository.findById(order.getId()).isPresent()) {
            order.setDate(order.getDate());
            order.setOrderType(order.getOrderType());
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    public boolean deleteOrder(Long id) {
        if (orderRepository.findById(id).isPresent()) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
