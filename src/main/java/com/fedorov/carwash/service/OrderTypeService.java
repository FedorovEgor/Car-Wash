package com.fedorov.carwash.service;

import com.fedorov.carwash.model.OrderType;
import com.fedorov.carwash.repository.OrderTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class OrderTypeService {

    private OrderTypeRepository orderTypeRepository;

    @Autowired
    public OrderTypeService(OrderTypeRepository orderTypeRepository) {
        this.orderTypeRepository = orderTypeRepository;
    }

    public OrderType getTypeById(Long id) {
        OrderType orderType = orderTypeRepository.findById(id).orElse(null);

        if (orderType == null) {
            throw new EntityNotFoundException("No order type found by passed id!");
        }

        return orderType;
    }

    public List<OrderType> getAllTypes() {
        return orderTypeRepository.findAll();
    }

    public void insertOrderType(OrderType orderType) {
        OrderType orderTypeFromDb = orderTypeRepository.getByDescription(orderType.getDescription()).orElse(null);

        if (orderTypeFromDb != null) {
            throw new IllegalArgumentException("Already in database!");
        }

        orderType.setDescription(orderType.getDescription());
        orderType.setDuration(orderType.getDuration());
        orderType.setPrice(orderType.getPrice());
        orderTypeRepository.save(orderType);
    }

    public boolean updateOrderType(OrderType orderType) {
        if (orderTypeRepository.findById(orderType.getId()).isPresent()) {
            orderType.setDescription(orderType.getDescription());
            orderType.setDuration(orderType.getDuration());
            orderType.setPrice(orderType.getPrice());
            orderTypeRepository.save(orderType);

            return true;
        }
        return false;
    }

    public boolean deleteOrderType(Long id) {
        if (orderTypeRepository.findById(id).isPresent()) {
            orderTypeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
