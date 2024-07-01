package com.example.ShopLaptop.Controller.client;

import com.example.ShopLaptop.Entity.Product;
import com.example.ShopLaptop.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Controller
public class ItemController {

    private final ProductService productService;
    @Autowired
    public ItemController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    public String productPage(Product product,Model model){
//        product = this.productService.getProductById()
        return "client/product/show";
    }

    @GetMapping("/products/{id}")
    public String productPageDetail(@PathVariable("id") Product product,Model model){
        model.addAttribute("product",product);
        return "client/product/detail";
    }



}
