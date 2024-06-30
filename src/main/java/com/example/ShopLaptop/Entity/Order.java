package com.example.ShopLaptop.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="orders")
public class Order {
    @Id
    private Long id;
    private Double totalPrice;
    private String receiverName;

    private String receiverAddress;

    private String receiverPhone;

    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    @OneToMany(mappedBy = "order")
    List<OrderDetail> orderDetails;

    public Long getId() {
        return id;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return users;
    }

    public void setUser(User user) {
        this.users = user;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
