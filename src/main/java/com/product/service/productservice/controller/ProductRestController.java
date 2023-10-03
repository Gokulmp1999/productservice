package com.product.service.productservice.controller;

import com.product.service.productservice.dto.Coupon;
import com.product.service.productservice.model.Product;
import com.product.service.productservice.repository.ProductRepository;
import com.product.service.productservice.utils.WebRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("productapi")
public class ProductRestController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${couponServiceUrl}")
    private String couponServiceUrl;

    @RequestMapping(value = "createProduct", method = RequestMethod.POST)
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Coupon coupon = WebRequest.microserviceCall(couponServiceUrl,product,restTemplate);
        product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
        Product SavedProduct = productRepository.save(product);
        return new ResponseEntity<>(SavedProduct, HttpStatus.CREATED);
    }

    @RequestMapping(value = "Product/{name}", method = RequestMethod.GET)
    public ResponseEntity<Product> createProduct(@PathVariable String name) {
        Product product = productRepository.findByName(name);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
