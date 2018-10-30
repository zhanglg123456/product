package com.fh.api.cart.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fh.api.util.BigDesimalJackson;

import java.io.Serializable;
import java.math.BigDecimal;

public class ItemVo implements Serializable {
    private static final long serialVersionUID = -1918844443288938589L;

    private Integer id;
    private String productName;
    private String productImage;
    @JsonSerialize(using = BigDesimalJackson.class)
    private BigDecimal price;
    @JsonSerialize(using = BigDesimalJackson.class)
    private BigDecimal totalFee;
    private Integer sum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }
}
