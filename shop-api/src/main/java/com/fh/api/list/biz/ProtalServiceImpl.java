package com.fh.api.list.biz;


import com.fh.api.common.ServerResponse;
import com.fh.api.list.mapper.ProtalMapper;
import com.fh.api.list.po.Product;
import com.fh.api.list.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("protalService")
public class ProtalServiceImpl implements ProtalService {

    @Autowired
    private ProtalMapper protalMapper;

    @Override
    public ServerResponse findList() {

        List<Product> products = protalMapper.findList();
        List<ProductVo> productVos = new ArrayList<>();
        for (Product product : products) {
            ProductVo productVo = new ProductVo();
            productVo.setId(product.getId());
            productVo.setProductName(product.getProductName());
            productVo.setMainImage(product.getMainImage());
            productVo.setPrice(product.getPrice());
            productVos.add(productVo);
        }

        return ServerResponse.success(productVos);
    }
}
