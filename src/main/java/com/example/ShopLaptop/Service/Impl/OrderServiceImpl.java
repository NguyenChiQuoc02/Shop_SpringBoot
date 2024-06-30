package com.example.ShopLaptop.Service.Impl;

import com.example.ShopLaptop.Entity.Order;
import com.example.ShopLaptop.Repository.OrderRepository;
import com.example.ShopLaptop.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrder() {
        return this.orderRepository.findAll();
    }

    @Override
    public Order saveOrder(Order order) {
        return this.orderRepository.save(order);
    }

    @Override
    public Order getOrderById(long id) {
        return this.orderRepository.getById(id);
    }

    @Override
    public void deleteOrder(long id) {
        this.orderRepository.deleteById(id);
    }
}
