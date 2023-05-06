package com.product.pocproducts.service.impl;

import com.product.pocproducts.dto.ProductCreateDTO;
import com.product.pocproducts.dto.ProductDTO;
import com.product.pocproducts.exception.PocProductError;
import com.product.pocproducts.exception.PocProductException;
import com.product.pocproducts.mapper.ProductMapper;
import com.product.pocproducts.model.Product;
import com.product.pocproducts.repository.ProductRepository;
import com.product.pocproducts.service.IProductService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository repository;

    private ProductMapper mapper;

    @Override
    public List<ProductDTO> findAll() {
        List<Product> products = this.repository.findAll();
        return mapper.from(products);
    }

    @Override
    @SneakyThrows
    public ProductDTO findByID(Long id) {
        Product product = this.repository
                .findById(id)
                .orElseThrow(() -> new PocProductException(PocProductError.USER_NOT_FOUND));
        return mapper.from(product);
    }

    @Override
    @SneakyThrows
    public ResponseEntity<ProductDTO> create(ProductCreateDTO product) {
        Product productEntity = mapper.to(product);
        Product save = repository.save(productEntity);
        return ResponseEntity.ok(mapper.from(save));
    }

    @Transactional
    @SneakyThrows
    public String delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new PocProductException(PocProductError.USER_NOT_FOUND));
        repository.deleteById(id);
        return HttpStatus.OK.toString();
    }

    @Override
    @SneakyThrows
    @Transactional
    public ProductDTO update(ProductDTO productDTO) {
        Product oldProduct = repository.findById(productDTO.getId())
                .orElseThrow(() -> new PocProductException(PocProductError.USER_NOT_FOUND));
        Product product = mapper.fromUpdate(productDTO,oldProduct);
        Product productSaved = Optional.of(repository.save(product))
                .orElseThrow(() -> new PocProductException(PocProductError.ERROR_UPDATE));
        return mapper.from(productSaved);
    }
}
