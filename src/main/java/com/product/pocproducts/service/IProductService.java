package com.product.pocproducts.service;

import com.azdevelopment.webproject.dto.UserDTO;
import com.product.pocproducts.dto.ProductCreateDTO;
import com.product.pocproducts.dto.ProductDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProductService {
    List<ProductDTO> findAll();

    ProductDTO findByID(String id);

    ResponseEntity<ProductDTO> create(ProductCreateDTO product);

    String delete(String id);

    ProductDTO update(ProductDTO product);
}
