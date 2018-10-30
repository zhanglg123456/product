package com.fh.biz.product;

import com.fh.common.ServerResponse;
import com.fh.po.product.Product;
import com.fh.vo.ProductVo;

public interface ProductService {
    void addproduct(Product product);

    ServerResponse findList(ProductVo productVo);

    Product findProductById(Integer id);

    ServerResponse updateProduct(ProductVo productVo, String realPath);

    void deleteProductById(Integer[] ids);
}
