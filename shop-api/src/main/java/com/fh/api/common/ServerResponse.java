package com.fh.api.common;

import java.io.Serializable;

/**
 * 同一返回的数据类型
 */
public class ServerResponse implements Serializable {
    private static final long serialVersionUID = 1701117307162979181L;
    private  int status;
    private  String massage;
    private  Object data;



    //构造方法私有化

    public static ServerResponse  success(Object data){


        return new ServerResponse(200,"ok",data);
    }
    public static ServerResponse  success(){


        return new ServerResponse(200,"ok");
    }

    public static ServerResponse  error(){


        return new ServerResponse(500,"error");
    }
    public static ServerResponse  error(Integer status,String massage){


        return new ServerResponse(status,massage);
    }
    public static ServerResponse  error(ResponseEnum responseEnum){


        return new ServerResponse(responseEnum.getStatus(),responseEnum.getMassage());
    }
    public static ServerResponse  success(ResponseEnum responseEnum){


        return new ServerResponse(responseEnum.getStatus(),responseEnum.getMassage());
    }


    private  ServerResponse(int status,String massage,Object data){
        this.status=status;
        this.massage=massage;
        this.data=data;
    }
    private  ServerResponse(int status,String massage){
        this.status=status;
        this.massage=massage;

    }

    public int getStatus() {
        return status;
    }

    public String getMassage() {
        return massage;
    }

    public Object getData() {
        return data;
    }
}
