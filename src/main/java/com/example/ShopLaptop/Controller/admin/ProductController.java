package com.example.ShopLaptop.Controller.admin;

import com.example.ShopLaptop.Entity.Product;
import com.example.ShopLaptop.Service.ProductService;
import com.example.ShopLaptop.Service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ProductController {

    private ProductService productService;
    private UploadService uploadService;
    @Autowired
    public ProductController(ProductService productService,UploadService uploadService) {
        this.productService = productService;
        this.uploadService = uploadService;
    }

    @GetMapping("admin/product")
    public String ProductPage(Model model) {
        List<Product> products = this.productService.getAllProduct();
        model.addAttribute("products",products);
        return "admin/product/show";
    }

    @GetMapping("admin/product/create")
    public String ProductPageCreate(Model model) {
        Product newProduct = new Product();
        model.addAttribute("newProduct",newProduct);
        return "admin/product/create";
    }
    @PostMapping("admin/product/create")
    public String ProductCreate(@ModelAttribute("newProduct") Product product,
                                @RequestParam("productFile") MultipartFile file) {

        String productImage = this.uploadService.UploadFile(file,"product");
        product.setImage(productImage);
        this.productService.saveProduct(product);
        return "redirect:/admin/product";
    }

    @GetMapping("admin/product/{id}")
    public String ProductDetail(@PathVariable("id") Product product, Model model) {
        model.addAttribute("product", product);
        return "admin/product/detail";
    }

    @GetMapping("admin/product/update/{id}")
    public String ProductPageUpdate(Model model, @PathVariable("id") Product product) {
        model.addAttribute("updateProduct",product);
        return "admin/product/update";
    }
    @PostMapping("admin/product/update")
    public String ProductUpdate(@ModelAttribute("updateProduct") Product product,
                                @RequestParam("productFileUpload") MultipartFile file) {
        Product currentProduct = this.productService.getProductById(product.getId());
        if (!file.isEmpty()) {
            String img = this.uploadService.UploadFile(file, "product");
            currentProduct.setImage(img);
        }

        currentProduct.setName(product.getName());
        currentProduct.setPrice(product.getPrice());
        currentProduct.setDetailDesc(product.getDetailDesc());
        currentProduct.setShortDesc(product.getShortDesc());
        currentProduct.setFactory(product.getFactory());
        currentProduct.setQuantity(product.getQuantity());
        currentProduct.setTarget(product.getTarget());
        this.productService.saveProduct(currentProduct);
        return "redirect:/admin/product";
    }

    @GetMapping("admin/product/delete/{id}")
    public String ProductDelete(@PathVariable("id") Long id) {
        this.productService.deleteProduct(id);
        return "redirect:/admin/product";
    }

}
