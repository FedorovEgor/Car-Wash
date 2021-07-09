package com.fedorov.carwash.restApi;

import com.fedorov.carwash.model.Order;
import com.fedorov.carwash.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrdersRestController {

    private OrderService orderService;

    @Autowired
    public OrdersRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrdersForCurrentUser() {
        List<Order> orders = orderService.getAllCurrentlyLoggedUsersOrders();

        return orders != null && !orders.isEmpty()
                ? new ResponseEntity<>(orders, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("{id}")
    public ResponseEntity<Order> getSingleOrderForCurrentUser(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);

        return order != null
                ? new ResponseEntity<>(order, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> addOrderToCurrentUser(@RequestBody Order order) {
        orderService.insertOrder(order);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateOrderOfCurrentUser(@RequestBody Order order) {
        boolean isUpdated = orderService.updateOrder(order);

        return isUpdated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOrderOfCurrentUser(@PathVariable Long id) {
        boolean isDeleted = orderService.deleteOrder(id);

        return isDeleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
