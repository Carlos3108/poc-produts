package com.product.pocproducts.controller;

import com.product.pocproducts.dto.ProductCreateDTO;
import com.product.pocproducts.dto.ProductDTO;
import com.product.pocproducts.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping("/create-form")
    public ModelAndView createForm() {
        return new ModelAndView("create-form");
    }

    @PostMapping("/create-th")
    public String createTh(ProductCreateDTO productCreateDTO) {
        iProductService.create(productCreateDTO);
        return "redirect:/home";
    }

    @GetMapping("/form-update/{id}")
    public ModelAndView formUpdate(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("update");
        modelAndView.addObject("product", iProductService.findByID(id));
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        iProductService.delete(id);
        return "redirect:/home";
    }

    @GetMapping("/update")
    public String update(@RequestParam Long id,
                               @RequestParam String name,
                               @RequestParam String validity,
                               @RequestParam Integer qtt,
                               @RequestParam String value) {
        ProductDTO productDTO = ProductDTO.builder()
                .id(id)
                .name(name)
                .validity(validity)
                .qtt(qtt)
                .value(new BigDecimal(value))
                .build();
        iProductService.update(productDTO);
        return "redirect:/home";
    }
}
