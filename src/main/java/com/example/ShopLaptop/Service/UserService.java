package com.example.ShopLaptop.Service;


import com.example.ShopLaptop.Entity.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUser();
    public User saveUser(User user);
    public  User getUserById(long id);
    public  void deleteUser( long id);
}
