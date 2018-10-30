package com.fh.httpclient;

import com.fh.api.test.CheckSumBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;

/**
 * @类描述：(请填写这个类的作用)
 * @作者        ：张鹿阁
 * @邮箱        : 200766243@qq.com
 * @创建日期    ：2018年09月29日 17:18
 */
public class HttpClientTest {

    private String appSecret = "c12570a6db994a6ca08da2dd2a6a98a7";
    private String appKey = "ac2c0124a47842b7b550dc40007dda1c";


    @Test
    public void  testList(){

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("http://localhost:8083/list/index.jhtml");


        httpGet.addHeader("appKey",appKey);
        String timeMillis = System.currentTimeMillis()+"";
        httpGet.addHeader("currTime",timeMillis);

        String nonce = UUID.randomUUID().toString()+System.currentTimeMillis()+ RandomStringUtils.randomAlphanumeric(10);

        String sign = CheckSumBuilder.getCheckSum(appSecret, nonce, timeMillis);
        httpGet.addHeader("sign",sign);
        httpGet.addHeader("nonce",nonce);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String s = EntityUtils.toString(entity);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null!=response){
                try {
                    response.close();
                    response = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null!=httpGet){
                httpGet.releaseConnection();
                httpGet = null;
            }
            if(null!= httpClient){
                try {
                    httpClient.close();
                    httpClient = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {

        String nonce = UUID.randomUUID().toString()+System.currentTimeMillis()+ RandomStringUtils.randomAlphanumeric(10);
        String currTime = System.currentTimeMillis() + "";
        System.out.println("currTime:"+currTime);

        System.out.println("nonce:"+nonce);


        String sign = CheckSumBuilder.getCheckSum("c12570a6db994a6ca08da2dd2a6a98a7", nonce, currTime);
        System.out.println("sign:"+sign);
    }


}
