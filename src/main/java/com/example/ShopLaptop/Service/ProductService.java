package com.example.ShopLaptop.Service;


import com.example.ShopLaptop.Entity.Cart;
import com.example.ShopLaptop.Entity.CartDetail;
import com.example.ShopLaptop.Entity.Product;
import com.example.ShopLaptop.Entity.User;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProduct();

    public Product saveProduct(Product product);

    public Product getProductById(long id);

    public void deleteProduct(long id);
    public void addProductToCart(String email, long id, HttpSession session);
    public Cart fetchByUser(User user);
    public void removeCartDetail(long id,HttpSession session);
    public void updateCartBeforeCheckout(List<CartDetail> cartDetails);
    public void handlePlaceOrder(
            User user, HttpSession session,
            String receiverName, String receiverAddress, String receiverPhone);
}