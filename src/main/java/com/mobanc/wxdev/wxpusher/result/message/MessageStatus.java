package com.mobanc.wxdev.wxpusher.result.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageStatus {
    private String uid;
    private Integer topicId;
    private Integer messageId;
    private Integer messageContentId;
    private Integer sendRecordId;
    private int code;
    private String status;
}
