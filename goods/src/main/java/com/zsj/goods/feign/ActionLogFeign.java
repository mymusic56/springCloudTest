package com.zsj.goods.feign;

import com.zsj.goods.feign.fallback.ActionLogFeignFallback;
import com.zsj.lib.utils.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "common-service", fallbackFactory = ActionLogFeignFallback.class)
public interface ActionLogFeign {
    @PostMapping("/common/action-log/save")
    public ResultData save(@RequestParam Map<String, Object> param);
}
