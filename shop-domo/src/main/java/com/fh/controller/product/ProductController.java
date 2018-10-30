package com.fh.controller.product;

import com.fh.biz.product.ProductService;
import com.fh.common.ResponseEnum;
import com.fh.common.ServerResponse;
import com.fh.common.SystemConst;
import com.fh.po.product.Product;
import com.fh.util.FileUtil;
import com.fh.vo.ProductVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class ProductController {

    @Resource(name = "productService")
    private ProductService productService;


    @RequestMapping("/product/deletById")
    @ResponseBody
    public ServerResponse deleteProductById(@RequestParam("ids[]") Integer [] ids){


        productService.deleteProductById(ids);

        return ServerResponse.success();

    }



    @RequestMapping("/product/update")
    @ResponseBody
    public  ServerResponse  updateProduct(ProductVo productVo,HttpServletRequest request){


        return productService.updateProduct(productVo,request.getServletContext().getRealPath("/"));
    }
;

    @RequestMapping("/product/findProductById")
    @ResponseBody
    public ServerResponse findProductById(Integer id){
        if(id<=0||null==id){

            return ServerResponse.error(ResponseEnum.ERROR_PRODUCT_PARAMETER);
        }

       Product product = productService.findProductById(id);

       if(null==product){


         return ServerResponse.error(ResponseEnum.ERROR_PRODUCT_NULL);
       }
        return ServerResponse.success(product);

    }

    @RequestMapping("/product/findList")
    @ResponseBody
    public ServerResponse  findList(ProductVo productVo){


        return productService.findList(productVo);
    }

    @RequestMapping("/product/upPhoto")
    @ResponseBody
    public  ServerResponse upManImg(@RequestParam MultipartFile up_mainImg, HttpServletRequest request){

        //是获得原文件名,是springMVC完成的文件上传。我们只需要转存就好了
        String oldFileName = up_mainImg.getOriginalFilename();
        //设置转存的文件路径
        String realPath = request.getServletContext().getRealPath(SystemConst.PHOTO_PATH);

        //调用工具类完成转存
        String newFileName = "";
        try {
            newFileName = FileUtil.copyFile(up_mainImg.getInputStream(), oldFileName, realPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ServerResponse.success(SystemConst.PHOTO_PATH+newFileName);


    }

    @RequestMapping("/product/addproduct")
    @ResponseBody
    public ServerResponse addproduct(Product product){

        productService. addproduct(product);
        return ServerResponse.success();
    }

    @RequestMapping("/product")
    public String toIndex(){

        return "product";

    }
}
