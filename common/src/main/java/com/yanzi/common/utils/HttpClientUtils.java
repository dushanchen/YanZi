package com.yanzi.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HttpClientUtils implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
    private PoolingHttpClientConnectionManager clientConnectionManager = null;
    private CloseableHttpClient httpClient = null;
    private RequestConfig requestConfig = null;
    
    public enum HttpRequestType {
        POST, GET;
    }

    @Value("#{configProperties['connection_max_total']}")
    private int connection_max_total = 1000;
    @Value("#{configProperties['connection_max_per_route']}")
    private int connection_max_per_route = 500;
    @Value("#{configProperties['connection_request_timeout']}")
    private int connection_request_timeout = 1000;
    @Value("#{configProperties['connection_timeout']}")
    private int connection_timeout = 1000;
    @Value("#{configProperties['socket_timeout']}")
    private int socket_timeout = 1000;

    @Override
    public void afterPropertiesSet() {
        clientConnectionManager = new PoolingHttpClientConnectionManager();
        clientConnectionManager.setMaxTotal(connection_max_total);
        clientConnectionManager.setDefaultMaxPerRoute(connection_max_per_route);
        httpClient = HttpClients.custom().setConnectionManager(clientConnectionManager).build();
        requestConfig = RequestConfig.custom()
                        .setConnectionRequestTimeout(connection_request_timeout)
                        .setConnectTimeout(connection_timeout)
                        .setSocketTimeout(socket_timeout)
                        .build();
    }
    
    public String getResponse(String url) {
        return getResponse(url, 3);
    }

    public String getResponse(String url, int retryTime) {
        return getResponse(url, null, HttpRequestType.GET, retryTime);
    }

    public String getResponse(String url, Map<String, String> parameters, int retryTime) {
        return getResponse(url, parameters, HttpRequestType.GET, retryTime);
    }

    public String getResponse(String url, Map<String, String> parameters,
            HttpRequestType requestType, int retryTimes) {
        String response = "";
        HttpUriRequest request;
        switch (requestType) {
            case POST:
                UrlEncodedFormEntity fromEntity = getPostNamePair(parameters);
                HttpPost httpPost = new HttpPost(url);
                httpPost.setConfig(requestConfig);
                httpPost.setEntity(fromEntity);
                request = httpPost;
                break;
            case GET:
                url = getGetUrl(url, parameters);
                url = StringUtils.remove(url, " ");
                HttpGet httpGet = new HttpGet(url);
                httpGet.setConfig(requestConfig);   
                request = httpGet;
                break;
            default:
                return response;
        }

        // retry n times
        int count = 0;
        while (retryTimes == -1? true : count++ < retryTimes) {
            try {
                response = getResponse(request);
                if (!StringUtils.isEmpty(response)) {
                    break;
                } else {
                    
                    System.out.println("Empty response when getting "+url+", Will retry for {} times:");
                    Thread.sleep(10000);
                }
            } catch (Exception e) {
                System.out.println("Empty response when getting "+url+", Will retry for {} times:");
            }
        }
        if(StringUtils.isEmpty(response)) {
            System.out.println("Empty response when getting "+url+"");
        }
        return response.trim();
    }

    @SuppressWarnings("deprecation")
    public UrlEncodedFormEntity getPostNamePair(Map<String, String> parameters) {
        UrlEncodedFormEntity formEntity = null;
        if (null == parameters) {
            return formEntity;
        }
        List<NameValuePair> paramPairs = new ArrayList<NameValuePair>();
        for (String key : parameters.keySet()) {
            String value = parameters.get(key);
            //paramPairs.add(new BasicNameValuePair(key, URLEncoder.encode(value)));
            try {
                paramPairs.add(new BasicNameValuePair(key, URLEncoder.encode(value, "utf-8")));
            } catch (UnsupportedEncodingException e) {
                logger.warn("failed encode value {} with utf-8", value);
                paramPairs.add(new BasicNameValuePair(key, URLEncoder.encode(value)));
            }
        }
        try {
            formEntity = new UrlEncodedFormEntity(paramPairs, HTTP.UTF_8);
        } catch (UnsupportedEncodingException e) {
            logger.warn("failed UrlEncodedFormEntity with utf-8");
        }
        return formEntity;
    }

    @SuppressWarnings("deprecation")
    public String getGetUrl(String url, Map<String, String> parameters) {
        if (null == parameters) {
            return url;
        }
        List<String> parametersList = new ArrayList<String>();
        for (String key : parameters.keySet()) {
            String value = parameters.get(key);
            try {
                parametersList.add(key + "=" + URLEncoder.encode(value, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                logger.warn("error encode value {} with utf-8", value);
                parametersList.add(key + "=" + URLEncoder.encode(value));
            }
        }
        return url + "?" + StringUtils.join(parametersList, "&");
    }

    private String getResponse(HttpUriRequest request) {
        String result = "";
        try {
            
            CloseableHttpResponse httpResponse = httpClient.execute(request);
            HttpEntity entity = httpResponse.getEntity();
//            httpResponse = new RemoteServerResponse(httpResponse.getStatusLine().getStatusCode(), 
//                    StringUtils.EMPTY);
            try {
                int status = httpResponse.getStatusLine().getStatusCode();
                if (status < 200 || status >= 300) {
                    logger.warn("http response status error");
                    return result;
                }
                
                if (entity == null) {
                    logger.warn("http response entity is null");
                    return result;
                }
                result = EntityUtils.toString(entity);
            } catch (Exception e) {
                logger.warn("occur exception:", e);
                return result;
            } finally {
                httpResponse.close();
            }
        } catch (Exception e) {
            logger.warn("occur exception:", e);
        }
        return result;
    }

}
