package com.example.ShopLaptop.Repository;

import com.example.ShopLaptop.Entity.Cart;
import com.example.ShopLaptop.Entity.CartDetail;
import com.example.ShopLaptop.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail,Long> {
    public boolean existsByCartAndProduct(Cart cart, Product product);
    public CartDetail findByCartAndProduct(Cart cart, Product product);
}
