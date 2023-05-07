package com.product.pocproducts.controller;

import com.product.pocproducts.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class ThymeleafController {
    private IProductService iProductService;
    @RequestMapping("/home")
    public String homePage() {
        return "home";
    }
    @GetMapping("/all")
    public ModelAndView listAll() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("products", iProductService.findAll());
        return modelAndView;
    }
}
