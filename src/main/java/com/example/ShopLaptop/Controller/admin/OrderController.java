package com.example.ShopLaptop.Controller.admin;

import com.example.ShopLaptop.Entity.Order;
import com.example.ShopLaptop.Entity.Product;
import com.example.ShopLaptop.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class OrderController {

    private OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("admin/order")
    public String OrderPage(Model model) {
        List<Order> orders = this.orderService.getAllOrder();
        model.addAttribute("orders",orders);
        return "admin/order/show";
    }

    @GetMapping("admin/order/create")
    public String OrderPageCreate(Model model) {
        Order newOrder = new Order();
        model.addAttribute("newOrder",newOrder);
        return "admin/order/create";
    }
    @PostMapping("admin/order/create")
    public String OrderCreate(@ModelAttribute("newOrder") Order order) {

        this.orderService.saveOrder(order);
        return "redirect:/admin/order";
    }

    @GetMapping("admin/order/{id}")
    public String OrderDetail(@PathVariable("id") Order order, Model model) {
        model.addAttribute("order", order);
        return "admin/order/detail";
    }

    @GetMapping("admin/order/update/{id}")
    public String OrderPageUpdate(Model model, @PathVariable("id") Order order) {
        model.addAttribute("updateOrder",order);
        return "admin/order/update";
    }
    @PostMapping("admin/order/update")
    public String ProductUpdate(@ModelAttribute("updateOrder") Order order) {

        return "redirect:/admin/order";
    }

    @GetMapping("admin/order/delete/{id}")
    public String ProductDelete(@PathVariable("id") Long id) {
        this.orderService.deleteOrder(id);
        return "redirect:/admin/order";
    }
}
