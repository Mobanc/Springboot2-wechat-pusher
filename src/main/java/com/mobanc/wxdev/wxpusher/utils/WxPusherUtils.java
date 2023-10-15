package com.mobanc.wxdev.wxpusher.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobanc.wxdev.wxpusher.result.callback.AppSubscribeCallback;
import com.mobanc.wxdev.wxpusher.config.WxPusherConfig;
import com.mobanc.wxdev.wxpusher.result.message.Message;
import com.mobanc.wxdev.wxpusher.result.message.MessageResult;
import com.mobanc.wxdev.wxpusher.result.message.Result;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

public class WxPusherUtils {

    private static final String API_URL = WxPusherConfig.sendMessageUrl;

    public static <T> Result<List<MessageResult>> send(Message message) {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 message 对象转换为 JSON 字符串
            String jsonBody = objectMapper.writeValueAsString(message);

            // 创建 POST 请求对象，并设置请求 URL
            HttpPost request = new HttpPost(API_URL);

            // 设置请求头部信息
            request.setHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType());

            // 设置请求体
            StringEntity entity = new StringEntity(jsonBody, ContentType.APPLICATION_JSON);
            request.setEntity(entity);

            // 发送请求并获取响应
            HttpResponse response = httpClient.execute(request);

            // 检查响应状态码
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                // 响应成功，解析响应内容
                String responseBody = EntityUtils.toString(response.getEntity());
                System.out.println(responseBody);
                // 使用 TypeReference 解析响应 JSON 字符串为 Result 对象
                Result<List<MessageResult>> result = objectMapper.readValue(responseBody, new TypeReference<Result<List<MessageResult>>>() {});

                return result;
            } else {
                // 响应失败，返回错误信息
                Result<List<MessageResult>> errorResult = new Result<>();
                errorResult.setSuccess(false);
                errorResult.setMsg("Failed to send message");
                return errorResult;
            }
        } catch (IOException e) {
            e.printStackTrace();
            // 发生异常，返回错误信息
            Result<List<MessageResult>> errorResult = new Result<>();
            errorResult.setSuccess(false);
            errorResult.setMsg("Exception during sending message");
            return errorResult;
        }
    }
    public static AppSubscribeCallback parseAppSubscribeCallback(String json) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, AppSubscribeCallback.class);
    }
}
