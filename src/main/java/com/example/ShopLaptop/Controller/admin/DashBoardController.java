package com.example.ShopLaptop.Controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashBoardController {

    @GetMapping("admin")
    public String getHomePage( ) {

        return "admin/dashboard/show";
    }

}
