package com.product.pocproducts.mapper;

import com.product.pocproducts.dto.ProductCreateDTO;
import com.product.pocproducts.dto.ProductDTO;
import com.product.pocproducts.exception.PocProductError;
import com.product.pocproducts.exception.PocProductException;
import com.product.pocproducts.model.Product;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ProductMapper {

    public List<ProductDTO> from(List<Product> products) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        products.forEach(product -> {
            productDTOS.add(ProductDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .validity(product.getValidity())
                    .qtt(product.getQtt())
                    .value(product.getValue())
                    .build());
        });
        return productDTOS;
    }

    public ProductDTO from(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .validity(product.getValidity())
                .qtt(product.getQtt())
                .value(product.getValue())
                .build();
    }

    public Product fromUpdate(ProductDTO newProductDTO, ProductDTO oldProduct) {
        return Product.builder()
                .id(oldProduct.getId())
                .name(isDiferent(newProductDTO.getName(), oldProduct.getName()))
                .validity(formatDate(isDiferent(newProductDTO.getValidity(), oldProduct.getValidity())))
                .qtt(isDiferentInt(newProductDTO.getQtt(), oldProduct.getQtt()))
                .value(isDiferentBig(newProductDTO.getValue(), oldProduct.getValue()))
                .build();
    }

    private Integer isDiferentInt(Integer newQtt, Integer oldQtt) {
        if (newQtt != null && !newQtt.equals(oldQtt))
            return newQtt;
        else
            return oldQtt;
    }

    private BigDecimal isDiferentBig(BigDecimal newValue, BigDecimal oldValue) {
        if (newValue != null && !newValue.equals(oldValue))
            return newValue;
        else
            return oldValue;
    }

    private static String isDiferent(String newValue, String oldValue) {
        if (newValue != null && !newValue.equals(oldValue))
            return isValid(newValue);
        else
            return isValid(oldValue);
    }

    @SneakyThrows
    private static String isValid(String value) {
        if (value.trim().isEmpty())
            throw new PocProductException(PocProductError.VALUE_IS_INVALID);
        return value;
    }

    public Product to(ProductCreateDTO productCreateDTO) {
        return Product.builder()
                .name(productCreateDTO.getName())
                .validity(formatDate(productCreateDTO.getValidity()))
                .qtt(productCreateDTO.getQtt())
                .value(productCreateDTO.getValue())
                .build();
    }

    private static String formatDate(String date) {
        Date formatDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(formatDate);
    }
}
