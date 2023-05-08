package com.product.pocproducts.service.impl;

import com.product.pocproducts.dto.ProductCreateDTO;
import com.product.pocproducts.dto.ProductDTO;
import com.product.pocproducts.exception.PocProductError;
import com.product.pocproducts.exception.PocProductException;
import com.product.pocproducts.mapper.ProductMapper;
import com.product.pocproducts.model.Product;
import com.product.pocproducts.repository.ProductRepository;
import com.product.pocproducts.service.IProductService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository repository;

    private ProductMapper mapper;

    @Override
    @SneakyThrows
    public List<ProductDTO> findAll() {
        List<Product> products = repository.findAll();
        return mapper.from(products);
    }

    @Override
    @SneakyThrows
    public ProductDTO findByID(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new PocProductException(PocProductError.PRODUCT_NOT_FOUND));
        return mapper.from(product);
    }

    @Override
    @SneakyThrows
    public ProductDTO findByName(String name) {
        Product product = repository.findByName(name)
                .orElseThrow(() -> new PocProductException(PocProductError.PRODUCT_NOT_FOUND));
        return mapper.from(product);
    }

    @Override
    @Transactional
    @SneakyThrows
    public ResponseEntity<ProductDTO> create(ProductCreateDTO product) {
        checkValue(product.getName());
        Optional<Product> productExists = repository.findByName(product.getName());
        if (productExists.isEmpty()) {
            Product save = repository.save(mapper.to(product));
            return ResponseEntity.created(URI.create("/product/create")).body(mapper.from(save));
        }
        return ResponseEntity.ok(mapper.from(productExists.get()));
    }

    @SneakyThrows
    public String delete(Long id) {
        findByID(id);
        repository.deleteById(id);
        return HttpStatus.OK.toString();
    }

    @Override
    @SneakyThrows
    @Transactional
    public ProductDTO update(ProductDTO productDTO) {
        ProductDTO oldProduct = findByID(productDTO.getId());
        Product product = mapper.fromUpdate(productDTO, oldProduct);
        Product productSaved = Optional.of(repository.save(product))
                .orElseThrow(() -> new PocProductException(PocProductError.ERROR_UPDATE));
        return mapper.from(productSaved);
    }

    @SneakyThrows
    private static void checkValue(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new PocProductException(PocProductError.VALUE_IS_INVALID);
        }
    }
}
