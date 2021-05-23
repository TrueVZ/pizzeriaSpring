package ru.mirea.springpizzashop.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mirea.springpizzashop.models.Product;
import ru.mirea.springpizzashop.models.Purchase;
import ru.mirea.springpizzashop.models.User;
import ru.mirea.springpizzashop.services.ProductService;
import ru.mirea.springpizzashop.services.ProductTypeService;
import ru.mirea.springpizzashop.services.PurchaseService;
import ru.mirea.springpizzashop.services.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;
    private final ProductService productService;
    private final ProductTypeService productTypeService;
    private final PurchaseService purchaseService;

    @Autowired
    public UserController(
                          ProductService productService,
                          ProductTypeService productTypeService,
                          UserService userService,
                          PurchaseService purchaseService) {
        this.productService = productService;
        this.productTypeService = productTypeService;
        this.userService = userService;
        this.purchaseService = purchaseService;
    }

    @GetMapping("/")
    public String index(){
        return "redirect:/home";
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
    public String index(Authentication authentication, Model model){

        model.addAttribute("productList", productService.getAllProducts());
        model.addAttribute("productTypeList", productTypeService.getAllProductTypes());
        return "home";
    }

    @GetMapping("/product/image/{id}")
    public void showImage(@PathVariable Long id, HttpServletResponse response) throws IOException {
        response.setContentType("image/png");
        InputStream is = new ByteArrayInputStream(productService.getPictureById(id));
        IOUtils.copy(is, response.getOutputStream());
    }

    @ResponseBody
    @PostMapping("/cart/addProduct/{productId}")
    public void addPurchase(Authentication authentication, @PathVariable("productId")Long id, Model model){
        User user = ((User)userService.loadUserByUsername(authentication.getName()));
        if(!authentication.isAuthenticated()){
            model.addAttribute("Error", "Not login");
        } else {
            long userId = user.getId();
            Purchase purchase = purchaseService.getPurchaseByUserIdAndProductId(userId, (long) id);
            if (purchase == null){
                Purchase newPurchase = new Purchase();
                newPurchase.setUserId(userId);
                newPurchase.setProductId((long) id);
                newPurchase.setProductCount(1);
                purchaseService.addPurchase(newPurchase);
            } else{
                purchase.setProductCount(purchase.getProductCount() + 1);
                purchaseService.addPurchase(purchase);
            }
        }
//        return "redirect:/home";
    }

    @GetMapping("/cart/delete/{purchaseId}")
    public String deletePurchase(@PathVariable("purchaseId")Long id){
        purchaseService.deletePurchase(id);
        return "redirect:/cart";
    }

    @GetMapping("/cart/check")
    public String checkOut(Authentication authentication){
        long userId = ((User) userService.loadUserByUsername(authentication.getName())).getId();
        purchaseService.deleteAllPurchaseByUserId(userId);
        return "redirect:/home";
    }


    @GetMapping("/cart")
    public String getCart(Authentication authentication, Model model){
        User user = ((User) userService.loadUserByUsername(authentication.getName()));
        int sum = 0;
        List<Purchase> purchaseList = purchaseService.getAllPurchaseUser(user.getId());
        for (Purchase purchase: purchaseList){
            sum += productService.getProductById(purchase.getProductId()).getPrice() * purchase.getProductCount();
        }
        model.addAttribute("total", sum);
        model.addAttribute("purchaseList", purchaseList);
        model.addAttribute("productService", productService);
        return "cart";
    }
}
