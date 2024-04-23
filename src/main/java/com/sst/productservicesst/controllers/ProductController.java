package com.sst.productservicesst.controllers;

import com.sst.productservicesst.dtos.ExceptionDto;
import com.sst.productservicesst.dtos.FakeStoreProductDto;
import com.sst.productservicesst.models.Product;
import com.sst.productservicesst.services.FakeStoreProductService;
import com.sst.productservicesst.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//This controller is capable to host HTTP API's
////localhost:8080/products -> ProductController
@RestController
@RequestMapping("/products")
public class ProductController { // waiter
    private ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    //localhost:8080/products/10
    @GetMapping("/{id}")
    public ResponseEntity getProductById(@PathVariable("id") Long id) {
        ResponseEntity<Product> responseEntity = null;
        Product product = null;
        try {
            product = productService.getProductById(id);
            responseEntity= new ResponseEntity<>(product, HttpStatus.OK);
            System.out.println("hello");
            return responseEntity;

        } catch(RuntimeException exception){

            ExceptionDto dto = new ExceptionDto();
            dto.setMessage("Something went wrong");
            dto.setResolution("do nothing");
            ResponseEntity<ExceptionDto> response = new ResponseEntity<>(dto,HttpStatus.NOT_FOUND);
            return response;

        }

    }

    //localhost:8080/products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

}