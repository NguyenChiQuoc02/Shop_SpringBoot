package com.example.ShopLaptop.Service;

import com.example.ShopLaptop.Entity.Order;


import java.util.List;

public interface OrderService {
    public List<Order> getAllOrder();

    public Order saveOrder(Order order);

    public Order getOrderById(long id);

    public void deleteOrder(long id);

}
