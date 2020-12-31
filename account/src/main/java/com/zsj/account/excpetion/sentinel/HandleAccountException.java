package com.zsj.account.excpetion.sentinel;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.netflix.client.http.HttpRequest;
import com.zsj.common.utils.ResultUtil;
import org.springframework.http.client.ClientHttpRequestExecution;

public class HandleAccountException {

    public static SentinelClientHttpResponse handleInfoException (HttpRequest request,
                                                                  byte[] body, ClientHttpRequestExecution execution, BlockException ex) {
        System.out.println("账户详情接口，流量超过限制！!!!");

        return new SentinelClientHttpResponse(ResultUtil.json(500, "账户详情接口，流量超过限制！", null));
    }
}
