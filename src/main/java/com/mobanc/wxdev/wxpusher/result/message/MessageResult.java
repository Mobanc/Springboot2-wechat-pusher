package com.mobanc.wxdev.wxpusher.result.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageResult {
    private int code;
    private String msg;
    private List<MessageStatus> data;
    private boolean success;
}
