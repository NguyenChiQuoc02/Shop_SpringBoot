package com.example.ShopLaptop.Service;


import com.example.ShopLaptop.Entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProduct();

    public Product saveProduct(Product product);

    public Product getProductById(long id);

    public void deleteProduct(long id);

}