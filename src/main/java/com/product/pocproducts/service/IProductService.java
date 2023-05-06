package com.product.pocproducts.service;

import com.product.pocproducts.dto.ProductCreateDTO;
import com.product.pocproducts.dto.ProductDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProductService {
    List<ProductDTO> findAll();

    ProductDTO findByID(Long id);

    ProductDTO findByName(String name);

    ResponseEntity<ProductDTO> create(ProductCreateDTO product);

    String delete(Long id);

    ProductDTO update(ProductDTO product);
}
