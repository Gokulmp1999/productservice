package com.product.service.productservice.utils;

import com.product.service.productservice.dto.Coupon;
import com.product.service.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

public class WebRequest {

    private static String username = "doug@bailey.com";
    private static String password = "doug";
    private static String credentials = username + ":" + password;
    private static  String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
    public static Coupon microserviceCall(String couponServiceUrl, Product product,RestTemplate restTemplate){
        // Set up headers with Basic Authentication
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + encodedCredentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        Coupon coupon =restTemplate.exchange(couponServiceUrl + product.getCouponCode(), HttpMethod.GET, entity, Coupon.class).getBody();
        return coupon;
    }
}
