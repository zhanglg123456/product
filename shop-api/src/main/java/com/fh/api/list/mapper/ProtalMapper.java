package com.fh.api.list.mapper;


import com.fh.api.list.po.Product;

import java.util.List;

public interface ProtalMapper {

    List<Product> findList();

    Product findListById(Integer id);
}
