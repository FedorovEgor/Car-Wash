package com.fedorov.carwash.restApi;

import com.fedorov.carwash.model.OrderType;
import com.fedorov.carwash.service.OrderTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/types")
public class OrderTypesRestController {

    private OrderTypeService orderTypeService;

    @Autowired
    public OrderTypesRestController(OrderTypeService orderTypeService) {
        this.orderTypeService = orderTypeService;
    }

    @GetMapping
    public ResponseEntity<List<OrderType>> getAllOrderTypes() {
        List<OrderType> orderTypes = orderTypeService.getAllTypes();

        return orderTypes != null && !orderTypes.isEmpty()
                ? new ResponseEntity<>(orderTypes, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderType> getSingleOrderType(@PathVariable Long id) {
        OrderType orderType = orderTypeService.getTypeById(id);

        return orderType != null
                ? new ResponseEntity<>(orderType, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> addOrderType(@RequestBody OrderType orderType) {
        orderTypeService.insertOrderType(orderType);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateOrderType(@RequestBody OrderType orderType) {
        boolean isUpdated = orderTypeService.updateOrderType(orderType);

        return isUpdated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOrderType(@PathVariable Long id) {
        boolean isDeleted = orderTypeService.deleteOrderType(id);

        return isDeleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
