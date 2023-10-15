package com.mobanc.wxdev.menu;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Deprecated
@Service
public class MenuService {
    private String appId = "";
    private String appSecret = "";

    private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
    private static final String QUERY_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=%s";
    private static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=%s";
    private static final String GET_CURRENT_SELFMENU_INFO_URL = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=%s";

    private RestTemplate restTemplate;

    public MenuService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void createMenu(String postData, String accessToken) {
        String postUrl = String.format(CREATE_MENU_URL, accessToken);
        // Send HTTP POST request to create menu
        // Implement this based on your Spring Boot application structure
        restTemplate.postForObject(postUrl, postData, String.class);
    }

    public void queryMenu(String accessToken) {
        String getUrl = String.format(QUERY_MENU_URL, accessToken);
        // Send HTTP GET request to query menu
        // Implement this based on your Spring Boot application structure
        // restTemplate.getForObject(getUrl, String.class);
    }

    public void deleteMenu(String accessToken) {
        String deleteUrl = String.format(DELETE_MENU_URL, accessToken);
        // Send HTTP GET request to delete menu
        // Implement this based on your Spring Boot application structure
        // restTemplate.getForObject(deleteUrl, String.class);
    }

    public void getCurrentSelfMenuInfo(String accessToken) {
        String infoUrl = String.format(GET_CURRENT_SELFMENU_INFO_URL, accessToken);
        // Send HTTP GET request to get current self menu info
        // Implement this based on your Spring Boot application structure
        // restTemplate.getForObject(infoUrl, String.class);
    }
}
