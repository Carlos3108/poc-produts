package com.product.pocproducts.mapper;

import com.product.pocproducts.dto.ProductCreateDTO;
import com.product.pocproducts.dto.ProductDTO;
import com.product.pocproducts.exception.PocProductError;
import com.product.pocproducts.exception.PocProductException;
import com.product.pocproducts.model.Product;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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

    public Product fromUpdate(ProductDTO productDTO ,Product product) {
        return Product.builder()
                .id(product.getId())
                .name(isDiferent(productDTO.getName(), product.getName()))
                .validity(isDiferentDate(productDTO.getValidity(), product.getValidity()))
                .qtt(isDiferentInt(productDTO.getQtt(), product.getQtt()))
                .value(isDiferentBig(productDTO.getValue(), product.getValue()))
                .build();
    }

    private Date isDiferentDate(Date newValidity, Date oldValidity) {
        if (newValidity != null && !newValidity.equals(oldValidity))
            return newValidity;
        else
            return oldValidity;
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
                .validity(productCreateDTO.getValidity())
                .qtt(productCreateDTO.getQtt())
                .value(productCreateDTO.getValue())
                .build();
    }
}