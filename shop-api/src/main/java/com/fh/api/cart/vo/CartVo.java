package com.fh.api.cart.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fh.api.util.BigDesimalJackson;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class CartVo implements Serializable {
    private static final long serialVersionUID = -8295784579719989602L;

    private Integer totalCount;
    @JsonSerialize(using = BigDesimalJackson.class)
    private BigDecimal totalPrice;
    private List<ItemVo> itemVoList = new ArrayList<>();

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }


    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<ItemVo> getItemVoList() {
        return itemVoList;
    }

    public void setItemVoList(List<ItemVo> itemVoList) {
        this.itemVoList = itemVoList;
    }
}
