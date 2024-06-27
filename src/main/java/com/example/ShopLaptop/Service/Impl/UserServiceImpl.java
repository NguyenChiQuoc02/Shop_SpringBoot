package com.example.ShopLaptop.Service.Impl;

import com.example.ShopLaptop.Entity.User;
import com.example.ShopLaptop.Repository.UserRepository;
import com.example.ShopLaptop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User getUserById(long id) {
        return this.userRepository.getById(id);
    }

    @Override
    public void deleteUser(long id) {
        this.userRepository.deleteById(id);
    }
}
