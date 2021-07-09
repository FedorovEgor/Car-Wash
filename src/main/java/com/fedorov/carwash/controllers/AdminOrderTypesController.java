package com.fedorov.carwash.controllers;

import com.fedorov.carwash.model.OrderType;
import com.fedorov.carwash.repository.OrderTypeRepository;
import com.fedorov.carwash.service.OrderTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/types")
public class AdminOrderTypesController {

    private OrderTypeService orderTypeService;

    @Autowired
    public AdminOrderTypesController(OrderTypeService orderTypeService) {
        this.orderTypeService = orderTypeService;
    }

    @GetMapping
    public String getAllTypes(Model model) {
        model.addAttribute("types", orderTypeService.getAllTypes());

        return "admin/types/index";
    }

    @GetMapping("/{id}")
    public String getSingleType(@PathVariable("id") Long id, Model model) {
        model.addAttribute("type", orderTypeService.getTypeById(id));

        return "admin/types/show";
    }

    @GetMapping("/new")
    public String newType(@ModelAttribute("type")OrderType orderType) {
        return "admin/types/new";
    }

    @PostMapping
    public String create(@ModelAttribute("type") @Valid OrderType orderType, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/types/new";
        }
        model.addAttribute(orderType);
        orderTypeService.insertOrderType(orderType);

        return "redirect:/admin/types";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("type", orderTypeService.getTypeById(id));
        return "admin/types/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("type") OrderType orderType) {
        orderTypeService.updateOrderType(orderType);
        return "redirect:/admin/types";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        orderTypeService.deleteOrderType(id);
        return "redirect:/admin/types";
    }
}
