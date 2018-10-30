package com.fh.api.common;

public enum ResponseEnum {

    ERROR_USER_PWD_COUNT_ERROR(10008,"密码错误三次，请找管理员解锁！"),
    ERROR_USER_OUT(10009,"您安全已退出登录"),
    ERROR_CART_COUNT(10222,"输入的商品数量不正确(0-10)"),

    ERROR_CART_NULL(10067,"购物车为空,请先去购物"),
    ERROR_HEADER_LOSE(30000,"请求头丢失"),

    ERROR_HEADER_TIMEOUT(30001,"请求超时"),
    ERROR_HEADER_MINOUT(30067,"规定时间内次数已达上限,稍后再试"),
    ERROR_HEADER_COUNT(30005,"接口限流,请勿频繁调用"),
    ERROR_HEADER_APPKEY(30002,"appkey不正确"),
    ERROR_HEADER_NONCE(30003,"非法的请求,系统被攻击了"),
    ERROR_HEADER_SIGN(30006,"签名错误"),
    ERROR_HEADER_SYSTEM(30008,"系统错误"),
    ERROR_USER_NULL(10020,"用户名或密码不允许为空!"),
    ERROR_PRODUCT_NULL(10066,"商品不存在或库存不足!"),
    ERROR_USER_REPEAT(10021,"用户名已存在"),
    SUCCESS_USER_OK(10000,"注册成功!"),
    ERROR_USER_PWD_EQUA(10004,"两次密码输入不一致!"),
    ERROR_USER_PHONE_NULL(10005,"手机号码不允许为空!"),
    ERROR_USER_PHONE_EQUA(10008,"此手机号已经被注册!"),
    ERROR_PHONE_CODE_BLANK(80001,"手机号码为空!"),
    ERROR_PHONE__FORMAT(80002,"手机号码格式不正确!"),
    ERROR_VIFCODE__BLANK(80003,"请填写验证码!"),
    ERROR_VIFCODE__TIMEOUT(80004,"验证码超时!"),
    ERROR_VIFCODE(80005,"验证码不正确,请重新获取!"),
    ERROR_VIFCODE_FORMAT(80006,"验证码位数不正确!");

    private  int status;
    private String massage;

    private ResponseEnum(int status,String massage){
        this.status = status;
        this.massage = massage;

    }

    public String getMassage() {
        return massage;
    }

    public int getStatus() {
        return status;
    }
}
