package com.example.project_2.DTO;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "OrderDetail")
public class OrderDetail {
    @EmbeddedId
    private OrderDetailId id;

    @MapsId("orderID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "OrderID", nullable = false)
    private Order orderID;

    @MapsId("vegetableID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "VegetableID", nullable = false)
    private Vegetable vegetableID;

    @Column(name = "Quantity", nullable = false)
    private Byte quantity;

    @Column(name = "Price", nullable = false)
    private Float price;

    public OrderDetailId getId() {
        return id;
    }

    public void setId(OrderDetailId id) {
        this.id = id;
    }

    public Order getOrderID() {
        return orderID;
    }

    public void setOrderID(Order orderID) {
        this.orderID = orderID;
    }

    public Vegetable getVegetableID() {
        return vegetableID;
    }

    public void setVegetableID(Vegetable vegetableID) {
        this.vegetableID = vegetableID;
    }

    public Byte getQuantity() {
        return quantity;
    }

    public void setQuantity(Byte quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

}