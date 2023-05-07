package com.product.pocproducts.controller;

import com.product.pocproducts.dto.ProductDTO;
import com.product.pocproducts.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

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

    @GetMapping("/form-update/{id}")
    public ModelAndView formUpdate(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("update");
        modelAndView.addObject("product", iProductService.findByID(id));
        return modelAndView;
    }

    @GetMapping("/update")
    public String update(@RequestParam Long id,
                               @RequestParam String name,
                               @RequestParam String validity,
                               @RequestParam Integer qtt,
                               @RequestParam BigDecimal value) {
        ProductDTO productDTO = ProductDTO.builder()
                .id(id)
                .name(name)
                .validity(validity)
                .qtt(qtt)
                .value(value)
                .build();
        iProductService.update(productDTO);
        return "home";
    }
}
