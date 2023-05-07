package com.product.pocproducts.controller;

import com.product.pocproducts.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class ThymeleafController {
    private IProductService iProductService;
    @GetMapping("/home")
    public ModelAndView listAll() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("products", iProductService.findAll());
        return modelAndView;
    }
    @GetMapping("form-create")
    public String formCreate(){
        return "form-create";
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("update");
        modelAndView.addObject("product", iProductService.findByID(id));
        return modelAndView;
    }
}
