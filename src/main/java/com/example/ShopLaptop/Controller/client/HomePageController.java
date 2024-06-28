package com.example.ShopLaptop.Controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("/")
    public String homePage(){
        return "client/homepage/show";
    }
    @GetMapping("/products")
    public String productPage(){
        return "client/product/show";
    }

}
