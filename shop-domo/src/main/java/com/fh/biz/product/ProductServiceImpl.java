package com.fh.biz.product;

import com.fh.common.ResponseEnum;
import com.fh.common.ServerResponse;
import com.fh.mapper.product.ProductMapper;
import com.fh.po.product.Product;
import com.fh.util.DataTableResult;
import com.fh.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void addproduct(Product product) {
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        productMapper.addproduct(product);
    }

    @Override
    public ServerResponse findList(ProductVo productVo) {

        //查询总条数
      int count =  productMapper.queryProductCount(productVo);
        //查询当前页内容
      List<Product> products = productMapper.findList(productVo);

        DataTableResult<Product> dataTableResult = new DataTableResult<>();
        dataTableResult.setDraw(productVo.getDraw());
        dataTableResult.setRecordsTotal(count);
        dataTableResult.setRecordsFiltered(count);
        dataTableResult.setData(products);
        return ServerResponse.success(dataTableResult);
    }

    @Override
    public Product findProductById(Integer id) {
        return productMapper.findProductById(id);
    }

    @Override
    public ServerResponse updateProduct(ProductVo productVo, String realPath) {

        if(null!=productVo){

            Product product = new Product();
            product.setId(productVo.getId());
            product.setProductName(productVo.getProductName());
            product.setUpdateTime(new Date());
            product.setPrice(productVo.getPrice());
            product.setMainImage(productVo.getMainImage());
            product.setStock(productVo.getStock());

            productMapper.updateProduct(product);
            //判断老图和新图是否相等，不相等就删除老图
            if(!productVo.getOldMainImg().equals(productVo.getMainImage())){

                File file = new File(realPath+productVo.getOldMainImg());
                if(file.exists()){
                    file.delete();
                }

            }
            return ServerResponse.success();
        }


        return ServerResponse.error(ResponseEnum.ERROR_PRODUCT_PARAMETER);
    }

    @Override
    public void deleteProductById(Integer[] ids) {
        productMapper.deleteProductById(ids);
    }
}
