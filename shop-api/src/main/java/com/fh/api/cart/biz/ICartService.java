package com.fh.api.cart.biz;

import com.fh.api.cart.vo.ItemVo;

import java.util.List;

public interface ICartService {

    List<ItemVo> addItem(List<ItemVo> itemList, Integer id, Integer sum);

    List<ItemVo> updateItem(List<ItemVo> itemList, Integer id, Integer sum);
}
