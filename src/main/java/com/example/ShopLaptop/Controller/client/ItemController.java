package com.example.ShopLaptop.Controller.client;

import com.example.ShopLaptop.Entity.Cart;
import com.example.ShopLaptop.Entity.CartDetail;
import com.example.ShopLaptop.Entity.Product;
import com.example.ShopLaptop.Entity.User;
import com.example.ShopLaptop.Service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ItemController {

    private final ProductService productService;
    @Autowired
    public ItemController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    public String productPage(Product product,Model model){
//        product = this.productService.getProductById()
        return "client/product/show";
    }

    @GetMapping("/products/{id}")
    public String productPageDetail(@PathVariable("id") Product product,Model model){
        model.addAttribute("product",product);
        return "client/product/detail";
    }

    @PostMapping("/add-product-to-cart/{id}")
    public String addProductToCart(@PathVariable long id , HttpServletRequest request){
        HttpSession session = request.getSession(false);
        long productId = id;
        String email = (String) session.getAttribute("email");
        this.productService.addProductToCart(email,productId, session);
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String getCartPage(Model model, HttpServletRequest request) {
        User currentUser = new User();// null
        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("id");
        currentUser.setId(id);

        Cart cart = this.productService.fetchByUser(currentUser);

        List<CartDetail> cartDetails = cart == null ? new ArrayList<CartDetail>() : cart.getCartDetails();

        double totalPrice = 0;
        for (CartDetail cd : cartDetails) {
            totalPrice += cd.getPrice() * cd.getQuantity();
        }

        model.addAttribute("cartDetails", cartDetails);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("cart", cart);

        return "client/cart/show";
    }
    @PostMapping("/delete-cart-product/{id}")
    public String deleteCartDetail(@PathVariable long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        long cartDetailId = id;
        this.productService.removeCartDetail(cartDetailId, session);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String getCheckOutPage(Model model, HttpServletRequest request) {
        User currentUser = new User();// null
        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("id");
        currentUser.setId(id);

        Cart cart = this.productService.fetchByUser(currentUser);

        List<CartDetail> cartDetails = cart == null ? new ArrayList<CartDetail>() : cart.getCartDetails();

        double totalPrice = 0;
        for (CartDetail cd : cartDetails) {
            totalPrice += cd.getPrice() * cd.getQuantity();
        }

        model.addAttribute("cartDetails", cartDetails);
        model.addAttribute("totalPrice", totalPrice);

        return "client/cart/checkout";
    }

    @PostMapping("/confirm-checkout")
    public String getCheckOutPage(@ModelAttribute("cart") Cart cart) {
        List<CartDetail> cartDetails = cart == null ? new ArrayList<CartDetail>() : cart.getCartDetails();
        this.productService.updateCartBeforeCheckout(cartDetails);
        return "redirect:/checkout";
    }

    @PostMapping("/place-order")
    public String handlePlaceOrder(
            HttpServletRequest request,
            @RequestParam("receiverName") String receiverName,
            @RequestParam("receiverAddress") String receiverAddress,
            @RequestParam("receiverPhone") String receiverPhone) {
        User currentUser = new User();// null
        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("id");
        currentUser.setId(id);

        this.productService.handlePlaceOrder(currentUser, session, receiverName, receiverAddress, receiverPhone);

        return "redirect:/thanks";
    }

    @GetMapping("/thanks")
    public String getThankYouPage(Model model) {

        return "client/cart/thanks";
    }

//    @PostMapping("/add-product-from-view-detail")
//    public String handleAddProductFromViewDetail(
//            @RequestParam("id") long id,
//            @RequestParam("quantity") long quantity,
//            HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//
//        String email = (String) session.getAttribute("email");
//        this.productService.handleAddProductToCart(email, id, session, quantity);
//        return "redirect:/product/" + id;
//    }

//    @GetMapping("/products")
//    public String getProductPage(Model model,
//                                 ProductCriteriaDTO productCriteriaDTO,
//                                 HttpServletRequest request) {
//        int page = 1;
//        try {
//            if (productCriteriaDTO.getPage().isPresent()) {
//                // convert from String to int
//                page = Integer.parseInt(productCriteriaDTO.getPage().get());
//            } else {
//                // page = 1
//            }
//        } catch (Exception e) {
//            // page = 1
//            // TODO: handle exception
//        }
//
//        // check sort price
//        Pageable pageable = PageRequest.of(page - 1, 10);
//
//        if (productCriteriaDTO.getSort() != null && productCriteriaDTO.getSort().isPresent()) {
//            String sort = productCriteriaDTO.getSort().get();
//            if (sort.equals("gia-tang-dan")) {
//                pageable = PageRequest.of(page - 1, 10, Sort.by(Product_.PRICE).ascending());
//            } else if (sort.equals("gia-giam-dan")) {
//                pageable = PageRequest.of(page - 1, 10, Sort.by(Product_.PRICE).descending());
//            }
//        }
//
//        Page<Product> prs = this.productService.fetchProductsWithSpec(pageable, productCriteriaDTO);
//
//        List<Product> products = prs.getContent().size() > 0 ? prs.getContent()
//                : new ArrayList<Product>();
//
//        String qs = request.getQueryString();
//        if (qs != null && !qs.isBlank()) {
//            // remove page
//            qs = qs.replace("page=" + page, "");
//        }
//
//        model.addAttribute("products", products);
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPages", prs.getTotalPages());
//        model.addAttribute("queryString", qs);
//        return "client/product/show";
//    }
}
