package com.product.pocproducts.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Error operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Object.class)))
    })
    @ExceptionHandler
    public ResponseEntity<Object> exceptionHandler(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
