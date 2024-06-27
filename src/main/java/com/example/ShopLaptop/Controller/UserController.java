package com.example.ShopLaptop.Controller;

import com.example.ShopLaptop.Entity.User;
import com.example.ShopLaptop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String getHomePage( ) {

        return "admin/dashboard/show";
    }

    @GetMapping("admin/user")
    public String UserPage(Model model) {
        List<User> users = userService.getAllUser();
        System.out.println(users);
        model.addAttribute("users",users);
        return "admin/user/show";
    }

    @GetMapping("admin/product")
    public String ProductPage() {

        return "admin/product/show";
    }

    @GetMapping("admin/order")
    public String OrderPage() {

        return "admin/order/show";
    }

    @GetMapping("admin/user/create")
    public String UserCreatePage(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);
        return "admin/user/create";
    }

    @PostMapping("admin/user/create")
    public String UserCreate(@ModelAttribute("newUser") User user) {
        userService.saveUser(user);
        return "redirect:/admin/user";
    }

    @GetMapping("admin/user/{id}")
    public String UserDetail(@PathVariable("id") User user, Model model) {
        model.addAttribute("user", user);
        return "admin/user/detail";
    }

    @GetMapping("admin/user/update/{id}")
    public String UserUpdatePage(@PathVariable("id") User user, Model model) {
        model.addAttribute("updateUser", user);
        return "admin/user/update";
    }

    @PostMapping("admin/user/update")
    public String UserUpdate(@ModelAttribute("updateUser") User user) {
        User currentUser = this.userService.getUserById(user.getId());
        currentUser.setEmail(user.getEmail());
        currentUser.setPhone(user.getPhone());
        currentUser.setFullName(user.getFullName());
        currentUser.setAddress(user.getAddress());
        this.userService.saveUser(currentUser);
        return "redirect:/admin/user";
    }

    @GetMapping("admin/user/delete/{id}")
    public String UserDelete(@PathVariable("id") Long id) {
        this.userService.deleteUser(id);
        return "redirect:/admin/user";
    }


}
