package com.fh.api.util;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {


    //post请求通用方法
    public static  String sendPost(String url, Map headers , Map params){
        //判断url是否为空
        if(StringUtils.isBlank(url)){
            new RuntimeException("url地址为空").printStackTrace();
        }
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);

        //设置请求头
        if(null != headers){
            //循环map
            Iterator iterator = headers.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry next = (Map.Entry) iterator.next();
                String key = (String) next.getKey();
                String value = (String) next.getValue();
                httpPost.addHeader(key,value);
            }
        }

        //设置请求body
        if(null != params){
            List<BasicNameValuePair> valuePairs = new ArrayList<>();
            Iterator iterator = params.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry next = (Map.Entry) iterator.next();
                String key = (String) next.getKey();
                String value = (String) next.getValue();
                BasicNameValuePair valuePair = new BasicNameValuePair(key,value);
                valuePairs.add(valuePair);
            }
            try {
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(valuePairs,"utf-8");
                httpPost.setEntity(formEntity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        CloseableHttpResponse response = null;
        String result = "";
        try {
            response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "utf-8");
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
            if(null!=httpPost){
                httpPost.releaseConnection();
                httpPost = null;
            }
            if (null != client){
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

    public static String sendGet(String url){

        CloseableHttpClient client = HttpClientBuilder.create().build();

        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        String result = "";
        try {
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "utf-8");



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
