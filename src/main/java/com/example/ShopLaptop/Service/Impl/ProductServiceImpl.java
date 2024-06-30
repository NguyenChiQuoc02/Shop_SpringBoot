package com.example.ShopLaptop.Service.Impl;

import com.example.ShopLaptop.Entity.Product;
import com.example.ShopLaptop.Repository.ProductRepository;
import com.example.ShopLaptop.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> getAllProduct() {
        return this.productRepository.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product getProductById(long id) {
        return this.productRepository.getById(id);
    }

    @Override
    public void deleteProduct(long id) {
        this.productRepository.deleteById(id);
    }
}
