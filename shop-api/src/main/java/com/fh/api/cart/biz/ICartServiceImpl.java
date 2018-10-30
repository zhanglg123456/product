package com.fh.api.cart.biz;

import com.fh.api.advice.GlobalException;
import com.fh.api.cart.vo.ItemVo;
import com.fh.api.common.ResponseEnum;
import com.fh.api.list.mapper.ProtalMapper;
import com.fh.api.list.po.Product;
import com.fh.api.util.BigDecimalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service("cartService")
public class ICartServiceImpl implements ICartService {

    @Autowired
    private ProtalMapper protalMapper;




    @Override
    public List<ItemVo> addItem(List<ItemVo> itemList, Integer id, Integer sum) {
        if (itemList==null){
            itemList=new ArrayList();
        }

        Product product=protalMapper.findListById(id);
        if(product==null){
            throw new GlobalException(ResponseEnum.ERROR_PRODUCT_NULL);
        }
        ItemVo itemVo = isExist(itemList, id);
        if(itemVo==null){
            ItemVo resultItem=new ItemVo();
            resultItem.setId(product.getId());
            resultItem.setProductName(product.getProductName());
            resultItem.setProductImage(product.getMainImage());
            resultItem.setPrice(product.getPrice());
            resultItem.setSum(sum);
            resultItem.setTotalFee(BigDecimalUtil.mult(product.getPrice().doubleValue(),sum.doubleValue()));
            itemList.add(resultItem);
        }else {
            itemVo.setSum(sum+itemVo.getSum());
            itemVo.setTotalFee(BigDecimalUtil.mult(itemVo.getPrice().doubleValue(),itemVo.getSum().doubleValue()));
            if(itemVo.getSum()<1){
                itemList.remove(itemVo);

            }

        }
        return itemList;
    }

    @Override
    public List<ItemVo> updateItem(List<ItemVo> itemList, Integer id, Integer sum) {

        if (itemList==null){
            throw new GlobalException(ResponseEnum.ERROR_PRODUCT_NULL);
        }

        Product product=protalMapper.findListById(id);
        if(product==null){
            throw new GlobalException(ResponseEnum.ERROR_PRODUCT_NULL);
        }
        ItemVo itemVo = isExist(itemList, id);
        if(null==itemVo){
            throw new GlobalException(ResponseEnum.ERROR_PRODUCT_NULL);

        }

        itemVo.setSum(sum);
        itemVo.setTotalFee(BigDecimalUtil.mult(itemVo.getPrice().doubleValue(),itemVo.getSum().doubleValue()));
        if(itemVo.getSum()<1){
            itemList.remove(itemVo);

        }

        return itemList;
    }

    public ItemVo isExist(List<ItemVo> itemList,Integer id){
        for (ItemVo itemVo : itemList) {
            if (itemVo.getId()==id){
                return itemVo;
            }
        }
        return null;
    }



}
