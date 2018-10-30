package com.fh.vo;

import com.fh.util.DataTablePage;

import java.io.Serializable;

public class ProductVo extends DataTablePage implements Serializable {
    private static final long serialVersionUID = 6342613213184321098L;

    private  String productName;
    private Double minPrice;
    private Double maxPrice;
    private Integer minStock;
    private  Integer maxStock;

    private String oldMainImg;
    private  Integer id;

    private  String mainImage;
    private  Integer stock;
    private  Double price;

    public String getOldMainImg() {
        return oldMainImg;
    }

    public void setOldMainImg(String oldMainImg) {
        this.oldMainImg = oldMainImg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getMinStock() {
        return minStock;
    }

    public void setMinStock(Integer minStock) {
        this.minStock = minStock;
    }

    public Integer getMaxStock() {
        return maxStock;
    }

    public void setMaxStock(Integer maxStock) {
        this.maxStock = maxStock;
    }
}
