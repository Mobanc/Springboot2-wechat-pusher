package com.mobanc.wxdev.wxpusher.result.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    public static final String CONTENT_TYPE_TEXT = "1";
    public static final String CONTENT_TYPE_HTML = "2";

    private String appToken;
    private String content;
    private String summary;
    private String contentType;
    private List<Integer> topicIds;
    private List<String> uids;
    private String url;
    private boolean verifyPay;
}
