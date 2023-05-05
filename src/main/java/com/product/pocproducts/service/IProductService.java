package com.product.pocproducts.service;

import com.azdevelopment.webproject.dto.UserDTO;
import com.product.pocproducts.dto.ProductDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProductService {
    List<ProductDTO> findAll();

    ProductDTO findByID(String id);

    ResponseEntity<ProductDTO> create(ProductDTO product);

    String delete(String id);

    ProductDTO update(ProductDTO product);
}
