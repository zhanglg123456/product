package com.fh.api.util;

import java.math.BigDecimal;

public class BigDecimalUtil {

    public  static BigDecimal add(String d1 ,String d2){
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.add(b2);
    }

    public static  BigDecimal sub(Double d1 ,Double d2){
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.subtract(b2);

    }


    public static BigDecimal mult(Double d1 ,Double d2){

        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        //精确到小数两位,进行四舍五入
        return b1.multiply(b2).setScale(2,BigDecimal.ROUND_HALF_UP);

    }

    public static BigDecimal divid(Double d1 ,Double d2){
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        //精确到小数两位,进行四舍五入
        return b1.divide(b2,2,BigDecimal.ROUND_HALF_UP);


    }
}
