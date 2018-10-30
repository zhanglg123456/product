package com.fh.api.cart.controller;

import com.fh.api.cart.biz.ICartService;
import com.fh.api.cart.vo.CartVo;
import com.fh.api.cart.vo.ItemVo;
import com.fh.api.common.ResponseEnum;
import com.fh.api.common.ServerResponse;
import com.fh.api.util.BigDecimalUtil;
import com.fh.api.util.CookieUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Resource(name = "cartService")
    private ICartService cartService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @RequestMapping(value = "updateItem", method = RequestMethod.POST)
    @CrossOrigin(value = "http://shop.fh.com:8081",allowCredentials = "true")//开启信任允许跨域返回cookie
    public ServerResponse updateItem(Integer id, Integer sum){

        //判断sum是否是合法的值
        if(sum<0 || sum>10){

            return ServerResponse.error(ResponseEnum.ERROR_CART_COUNT);
        }

        String fh_cart = CookieUtil.readerCookie(request, "fh_cart");

        if(StringUtils.isBlank(fh_cart)){

            return ServerResponse.error(ResponseEnum.ERROR_CART_NULL);
        }

        Gson gson = new Gson();
        List<ItemVo> itemList = gson.fromJson(fh_cart, new TypeToken<List<ItemVo>>() {}.getType());
        itemList = cartService.updateItem(itemList,id,sum);
        String itemStr = gson.toJson(itemList);
        CookieUtil.writerCookie(response,"fh_cart",itemStr,".fh.com",7*24*60*60);
        return ServerResponse.success();

    }

    @RequestMapping(value = "/addItem",method = RequestMethod.POST)
    @CrossOrigin(value = "http://shop.fh.com:8081",allowCredentials = "true")//开启信任允许跨域返回cookie
    public ServerResponse addItem(Integer id, Integer sum){


        String fh_cart = CookieUtil.readerCookie(request, "fh_cart");
        if(StringUtils.isBlank(fh_cart)){
            fh_cart="[]";
            //return ServerResponse.error(ResponseEnum.ERROR_CART_NULL);
        }
        Gson gson = new Gson();
        List<ItemVo> itemList = gson.fromJson(fh_cart, new TypeToken<List<ItemVo>>() {}.getType());
        itemList = cartService.addItem(itemList,id,sum);
        String itemStr = gson.toJson(itemList);
        CookieUtil.writerCookie(response,"fh_cart",itemStr,".fh.com",7*24*60*60);
        return ServerResponse.success();
    }
    @RequestMapping("/queryCart")
    @CrossOrigin(value = "http://shop.fh.com:8081" ,allowCredentials = "true")
    public ServerResponse queryCart(){
        String fh_cart = CookieUtil.readerCookie(request, "fh_cart");
        if(StringUtils.isBlank(fh_cart)){
            return ServerResponse.error(ResponseEnum.ERROR_CART_NULL);
        }
        Gson gson=new Gson();
        List<ItemVo> itemList = gson.fromJson(fh_cart, new TypeToken<List<ItemVo>>() {}.getType());
        CartVo cartVo=new CartVo();
        int productCount= 0;
        BigDecimal totalPrice = new BigDecimal("0");
        for (ItemVo itemVo : itemList) {
            productCount +=itemVo.getSum();
            totalPrice = BigDecimalUtil.add(totalPrice+"",itemVo.getTotalFee()+"");
        }
        cartVo.setTotalPrice(totalPrice);
        cartVo.setTotalCount(productCount);
        cartVo.setItemVoList(itemList);
        return ServerResponse.success(cartVo);
    }


}
