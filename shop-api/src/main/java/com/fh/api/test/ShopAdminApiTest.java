package com.fh.api.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class ShopAdminApiTest {

    public static String sendHttpDelete(String url,String ids){
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpDelete httpDelete = new HttpDelete(url+"?ids="+ids);

        CloseableHttpResponse response = null;
        String result = "";
        try {
            response = client.execute(httpDelete);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
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
            if (null != httpDelete){
                httpDelete.releaseConnection();
            }
            if(null!=client){
                try {
                    client.close();
                    client = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;


    }

    public static String sendHttpGet1() throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();

        HttpGet httpGet = new HttpGet("http://192.168.0.134:8080/products?drow=1&start=0&length=3");

        CloseableHttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity,"utf-8");
        System.out.println(result);
        return result;


    }

    public static String sendHttpGet(String url,Map header){

        String param = "";

        if(header!=null){
            Iterator iterator = header.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry next = (Map.Entry) iterator.next();
                String  key = (String) next.getKey();
                Integer  value = (Integer) next.getValue();
                param+="&"+key+"="+value+"";
            }
        }
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url+"?"+param);
        CloseableHttpResponse response = null;
        String result = "";
        try {
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
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
            if (null != httpGet){
                httpGet.releaseConnection();
            }
            if(null!=client){
                try {
                    client.close();
                    client = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;

    }

    public static String sendHttpPut(String url,String json){

        CloseableHttpClient client = HttpClientBuilder.create().build();

        HttpPut httpPut = new HttpPut(url);

        httpPut.setHeader("Content-Type","application/json");

        StringEntity stringEntity = new StringEntity(json, "utf-8");

        httpPut.setEntity(stringEntity);
        CloseableHttpResponse response = null;
        String result = "";
        try {
            response = client.execute(httpPut);

            HttpEntity entity = response.getEntity();

            result = EntityUtils.toString(entity);

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
            if (null != httpPut){
                httpPut.releaseConnection();
            }
            if(null!=client){
                try {
                    client.close();
                    client = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

        }

        return result;
    }
}
