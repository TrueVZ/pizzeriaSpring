package ru.mirea.springpizzashop.controllers;

import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mirea.springpizzashop.models.Product;
import ru.mirea.springpizzashop.models.User;
import ru.mirea.springpizzashop.services.ProductService;
import ru.mirea.springpizzashop.services.ProductTypeService;
import ru.mirea.springpizzashop.services.UserService;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;
    private final ProductService productService;
    private final ProductTypeService productTypeService;

    @Autowired
    public UserController(
                          ProductService productService,
                          ProductTypeService productTypeService,
                          UserService userService) {
        this.productService = productService;
        this.productTypeService = productTypeService;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegister(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute("user") User userForm, Map<String, Object> model){
        if(!userService.addUser(userForm)){
            model.put("message", "User exists");
            return "/register";
        }
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String index(Model model){
        model.addAttribute("productList", productService.getAllProducts());
        model.addAttribute("productTypeList", productTypeService.getAllProductTypes());
        return "home";
    }
}
