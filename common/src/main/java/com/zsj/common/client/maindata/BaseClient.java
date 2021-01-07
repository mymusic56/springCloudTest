package com.zsj.common.client.maindata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zsj.common.handler.CustomConfigHandler;
import com.zsj.common.utils.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BaseClient {
    private String redisTokenKey = "main-data:token";

    protected String token = "";

    private int retryTimes = 0;

    @Autowired
    CustomConfigHandler configHandler;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    protected String token_uri = "/api/admin/api/client-token";

    protected String ebp_list_uri = "/api/databack/mdstand/sp/list";
    protected String org_list_uri = "/api/databack/mdstand/org/list";
    protected String trader_list_uri = "/api/databack/mdstand/merchant/list";
    protected String trader_page_list_uri = "/api/databack/mdstand/merchant/pageList";

    protected String sepec_page_list_uri = "/api/databack/mdstand/specs/pageList";
    protected String brand_list_uri = "/api/databack/mdstand/brand/list";
    protected String brand_page_list_uri = "/api/databack/mdstand/brand/pageList";
    protected String type_list_uri = "/api/databack/mdstand/type/list";

    public String getToken() throws MainDataException {

        String token = stringRedisTemplate.opsForValue().get(this.redisTokenKey);
        if (token != null && !"".equals(token)) {
            this.token = token;
            return token;
        }

        RestTemplate template = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        MultiValueMap<String,String> param = new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(param, headers);

        String url = configHandler.getMainDataBaseUrl() + token_uri +
                "?clientId=" + configHandler.getMainDataClientId() +
                "&clientSecret=" + configHandler.getMainDataSecret();

        ResponseEntity<String> responseEntity = template.postForEntity(url, httpEntity, String.class);

        if (responseEntity.getStatusCodeValue() != 200) {
            throw new MainDataException("网络请求异常，获取token失败！");
        }

        JSONObject result = JSONObject.parseObject(responseEntity.getBody());
        this.token = result.getString("data");

        //更新缓存
        stringRedisTemplate.opsForValue().set(redisTokenKey, this.token, configHandler.getMainDataExpires(), TimeUnit.SECONDS);

        return this.token;
    }

    public void refreshToken() throws MainDataException {
        stringRedisTemplate.delete(redisTokenKey);
        getToken();
    }


    protected String post(String uri, Map<String, String> param) throws MainDataException {
        RestTemplate template = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json;charset=UTF-8");
        headers.set("Authorization", "Bearer " + this.token);

//        LinkedMultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
//
//        for (Map.Entry<String, String> entry: param.entrySet()) {
//            paramMap.add(entry.getKey(), entry.getValue());
//        }
//
        HttpEntity<String> httpEntity = new HttpEntity(JSON.toJSONString(param), headers);

        try {
            ResponseEntity<String> responseEntity = template.postForEntity(uri, httpEntity, String.class);
            if (responseEntity.getStatusCodeValue() != 200) {
                throw new MainDataException("网络请求失败:"+responseEntity.getBody());
            }
            return responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == 401 && retryTimes < 1) {
                //刷新token
                refreshToken();
                retryTimes++;
                post(uri, param);
            }
        }
        return "";
    }

}
