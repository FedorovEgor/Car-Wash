package com.fedorov.carwash.controllers;

import com.fedorov.carwash.model.Order;
import com.fedorov.carwash.service.OrderService;
import com.fedorov.carwash.service.OrderTypeService;
import com.fedorov.carwash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class MyOrdersController {

    private OrderService orderService;
    private UserService userService;
    private OrderTypeService orderTypeService;

    @Autowired
    public MyOrdersController(OrderService orderService, UserService userService, OrderTypeService orderTypeService) {
        this.orderService = orderService;
        this.userService = userService;
        this.orderTypeService = orderTypeService;
    }

    @GetMapping()
    public String getOrders(Model model) {
        model.addAttribute("orders", orderService.getAllCurrentlyLoggedUsersOrders());
        return "orders/index";
    }

    @GetMapping("/new")
    public String newOrder(Model model, @ModelAttribute("order") Order order) {
        model.addAttribute("types", orderTypeService.getAllTypes());
        return "orders/new";
    }

    @PostMapping()
    public String createOrder(@ModelAttribute("order") Order order, Model model) {
        model.addAttribute(order);

        orderService.insertOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("order", orderService.getOrderById(id));
        model.addAttribute("types", orderTypeService.getAllTypes());
        return "orders/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("order") Order order) {
        orderService.updateOrder(order);
        orderService.getAllCurrentlyLoggedUsersOrders();
        return "redirect:/orders";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        orderService.getAllCurrentlyLoggedUsersOrders();
        return "redirect:/orders";
    }
}
