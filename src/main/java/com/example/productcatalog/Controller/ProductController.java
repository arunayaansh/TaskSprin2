package com.example.productcatalog.Controller;

import com.example.productcatalog.Entity.Product;
import com.example.productcatalog.Service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/add-product")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @PostMapping("/add-product")
    public String saveProduct(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/display-products";
    }

    @GetMapping("/display-products")
    public String displayProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        //System.out.println("Products fetched: " + products);
        model.addAttribute("products", products);
        return "display-products";
    }


    @GetMapping("/product/{id}")
    public String getProductById(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product-details";
    }
}

