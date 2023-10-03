package com.product.service.productservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Coupon {
    private Long id;
    private String code;
    private BigDecimal discount;
    private String expDate;
}
