package com.fh.api.list.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fh.api.util.BigDesimalJackson;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductVo implements Serializable {

    private static final long serialVersionUID = 8171345589894724474L;
    private Integer id;

    private String productName;

    private String mainImage;

    @JsonSerialize(using = BigDesimalJackson.class)
    private BigDecimal price;

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

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
