package com.example.project_2.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderDetailId implements Serializable {
    @Serial
    private static final long serialVersionUID = 2231940940336751324L;

    @Column(name = "OrderID", nullable = false)
    private Integer orderID;

    @Column(name = "VegetableID", nullable = false)
    private Integer vegetableID;

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getVegetableID() {
        return vegetableID;
    }

    public void setVegetableID(Integer vegetableID) {
        this.vegetableID = vegetableID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderDetailId entity = (OrderDetailId) o;
        return Objects.equals(this.orderID, entity.orderID) &&
                Objects.equals(this.vegetableID, entity.vegetableID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, vegetableID);
    }

}