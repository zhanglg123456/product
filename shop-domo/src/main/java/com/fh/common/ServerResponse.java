package com.fh.common;

import java.io.Serializable;

public class ServerResponse implements Serializable {
    private static final long serialVersionUID = 1701117307162979181L;

    private  int code;
    private String massage;
    private Object data;

    public static  ServerResponse success(Object data){


        return new ServerResponse(200,"ok",data);
    }
    public static  ServerResponse success(){


        return new ServerResponse(200,"ok");
    }

    public static  ServerResponse error(){


        return new ServerResponse(500,"error");
    }

    public static  ServerResponse error(ResponseEnum responseEnum){


        return new ServerResponse(responseEnum.getCode(),responseEnum.getMassage());
    }
    private  ServerResponse(int code,String massage,Object data){
        this.code = code;
        this.massage = massage;
        this.data = data;


    }
    private  ServerResponse(int code,String massage){
        this.code = code;

        this.massage = massage;

    }

    public int getCode() {
        return code;
    }

    public String getMassage() {
        return massage;
    }

    public Object getData() {
        return data;
    }
}
