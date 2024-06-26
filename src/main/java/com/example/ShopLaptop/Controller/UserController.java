package com.example.ShopLaptop.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("")
    public String getHomePage() {

        return "admin/dashboard/show";
    }

    @RequestMapping("admin/user")
    public String UserPage() {

        return "admin/user/show";
    }
}
