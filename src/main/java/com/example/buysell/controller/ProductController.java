package com.example.buysell.controller;

import com.example.buysell.models.Product;
import com.example.buysell.models.User;
import com.example.buysell.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;//final потому что при создании бина он его сразу инжектит

    @GetMapping("/")//этот код очень интересен 18 строка
    public String products(@RequestParam(name = "title", required = false) String title,Principal principal, Model model) {
        model.addAttribute("products", productService.listProducts(title));
        model.addAttribute("user",productService.getUserByPrincipal(principal));//6L
        return "products";
    }

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
       Product product = productService.getProductByID(id);
        model.addAttribute("product",product);
        model.addAttribute("images",product.getImages());
        return "product-info";
    }


    @PostMapping("/product/create")
    public String create(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                         @RequestParam("file3") MultipartFile file3, Product product, Principal principal) {
        productService.saveProduct(principal,product, file1, file2, file3);
        return "redirect:/";//эта строка обновляет страницу после того как мы добавили товар
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/";//эта строка обновляет страницу после того как мы добавили товар
    }
    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model) {
        model.addAttribute("user",user);
        model.addAttribute("products",user.getProducts());

        return "user-info";
    }
}

