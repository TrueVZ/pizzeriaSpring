package ru.mirea.springpizzashop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.mirea.springpizzashop.models.Product;
import ru.mirea.springpizzashop.services.ProductService;
import ru.mirea.springpizzashop.services.ProductTypeService;
import ru.mirea.springpizzashop.services.UserService;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final ProductService productService;
    private final ProductTypeService productTypeService;

    @GetMapping
    public String index(Model model){
        return "admin/index";
    }


    @GetMapping("/products")
    public String products(Model model){
        model.addAttribute("listProducts", productService.getAllProducts());
        model.addAttribute("listProductType", productTypeService.getAllProductTypes());
        return "admin/products";
    }

    @GetMapping("/products/{id}")
    public String detailProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.getProductById((long) id));
        model.addAttribute("listProductType", productTypeService.getAllProductTypes());
        return "admin/detail_product";
    }

    @GetMapping("/delete_product/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        productService.deleteProduct((long) id);
        return "redirect:/admin/products";
    }

    @PostMapping("/products/create")
    public String createProduct(@RequestParam int productTypeId, @RequestParam String name, @RequestParam int price, @RequestParam String description, @RequestParam MultipartFile image) {
        Product product = new Product();
        product.setProductType(productTypeService.getProductTypeById((long) productTypeId));
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        try {
            product.setPicture(image.getBytes());
        } catch (IOException e){
            e.printStackTrace();
        }

        productService.addProduct(product);

        return "redirect:/admin/products";
    }
}
