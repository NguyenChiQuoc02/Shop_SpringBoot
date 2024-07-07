package com.example.ShopLaptop.Repository;

import com.example.ShopLaptop.Entity.Cart;
import com.example.ShopLaptop.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    public Cart findByUser(User user);
}
