package com.fh.mapper.product;

import com.fh.po.product.Product;
import com.fh.vo.ProductVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    void addproduct(Product product);

    List<Product> findList(ProductVo productVo);

    int queryProductCount(ProductVo productVo);

    Product findProductById(Integer id);

    void updateProduct(Product product);

    void deleteProductById(@Param("ids") Integer[] ids);
}
