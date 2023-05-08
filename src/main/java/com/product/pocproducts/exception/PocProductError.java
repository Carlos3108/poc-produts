package com.product.pocproducts.exception;

import lombok.Getter;

public enum PocProductError {

    PRODUCT_NOT_FOUND("Product not found."),

    ERROR_CREATING("When creating the product the ID must be Null"),

    ERROR_UPDATE("When updating the product, the ID must be filled in."),

    VALUE_IS_INVALID("Invalid value.");
    @Getter
    private final String message;

    PocProductError(String message) {
        this.message = message;
    }
}
