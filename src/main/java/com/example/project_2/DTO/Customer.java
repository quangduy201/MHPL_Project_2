package com.example.project_2.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Customers")
public class Customer {
    @Id
    @Column(name = "CustomerID", nullable = false)
    private Integer id;

    @Column(name = "Password", nullable = false, length = 20)
    private String password;

    @Column(name = "Fullname", nullable = false, length = 40)
    private String fullname;

    @Column(name = "Address", length = 50)
    private String address;

    @Column(name = "City", length = 20)
    private String city;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}