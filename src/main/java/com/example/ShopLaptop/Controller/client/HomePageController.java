package com.example.ShopLaptop.Controller.client;

import com.example.ShopLaptop.Entity.Product;
import com.example.ShopLaptop.Entity.User;
import com.example.ShopLaptop.Entity.dto.RegisterDTO;
import com.example.ShopLaptop.Service.ProductService;
import com.example.ShopLaptop.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class HomePageController {
    private final ProductService productService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public HomePageController(ProductService productService,UserService userService,PasswordEncoder passwordEncoder) {
        this.productService = productService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("")
    public String homePage(Model model){
        List<Product> products = this.productService.getAllProduct();
        model.addAttribute("products",products);
        return "client/homepage/show";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerUser", new RegisterDTO());
        return "client/auth/register";
    }

    @PostMapping("/register")
    public String getRegister(@ModelAttribute("registerUser") @Valid  RegisterDTO registerDTO,
                              BindingResult bindingResult){
        // validate
        if (bindingResult.hasErrors()) {
            return "client/auth/register";
        }
        User user = this.userService.registerDTOtoUser(registerDTO);
        String hashPassword = this.passwordEncoder.encode(registerDTO.getPassword());
        user.setPassword(hashPassword);
        user.setRole(this.userService.getRoleByName("USER"));
        this.userService.saveUser(user);
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String getLoginrPage(Model model){

        return "client/auth/login";
    }

    @GetMapping("/access-deny")
    public String getDeny(){

        return "client/auth/deny";
    }


}
