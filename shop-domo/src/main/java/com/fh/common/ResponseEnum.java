package com.fh.common;

public enum ResponseEnum {
    ERROR_USER_NAME(10001,"用户名输入错误！"),
    ERROR_USER_PWD(10002,"密码输入错误！"),
    REEOR_USER_NULL(10003,"用户名不能为空！"),
    SUCCESS_USER_OK(10000,"登录成功！"),
    ERROR_USER_CODE(10004,"验证码输入错误"),
    ERROR_USER_CODE_NULL(10005,"验证码为空！"),
    ERROR_USER_PWD_COUNT_ERROR(10008,"密码错误三次，请找管理员解锁！"),
    ERROR_USER_OUT(10009,"您安全已退出登录"),
    ERROR_PRODUCT_NULL(50001,"找不到该商品！"),
    ERROR_PRODUCT_PARAMETER(50002,"参数异常！");

    private  int code;
    private String massage;

    private ResponseEnum(int code, String massage){
        this.code = code;
        this.massage = massage;

    }

    public int getCode() {
        return code;
    }

    public String getMassage() {
        return massage;
    }
}
