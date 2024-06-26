package com.example.ShopLaptop.Controller.admin;

import com.example.ShopLaptop.Entity.User;
import com.example.ShopLaptop.Service.UploadService;
import com.example.ShopLaptop.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService,UploadService uploadService,PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadService =uploadService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("admin/user")
    public String UserPage(Model model) {
        List<User> users = userService.getAllUser();
        System.out.println(users);
        model.addAttribute("users",users);
        return "admin/user/show";
    }


    @GetMapping("admin/user/create")
    public String UserCreatePage(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);
        return "admin/user/create";
    }

    @PostMapping("admin/user/create")
    public String UserCreate(@ModelAttribute("newUser") User user,
                             @RequestParam("avatarUser") MultipartFile file
                            ) {
        String avatar = this.uploadService.UploadFile(file, "avatar");
        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setAvatar(avatar);
        user.setPassword(hashPassword);
        user.setRole(this.userService.getRoleByName(user.getRole().getName()));
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
