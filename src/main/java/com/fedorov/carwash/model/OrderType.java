package com.fedorov.carwash.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "order_types")
public class OrderType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "type_id")
    private Long id;
    @NotEmpty(message = "Description should not be empty!")
    @Column(name = "description")
    private String description;
    @Min(value = 1, message = "Should be at least 1 minute")
    @Column(name = "duration_mins")
    private Integer duration;
    @Min(value = 1, message = "Should be at leasst 1 dollar")
    @Column(name = "price_dollars")
    private Integer price;
    @OneToMany(mappedBy = "orderType")
    private List<Order> orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}
