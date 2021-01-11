package com.zsj.goods.feign.fallback;


import com.zsj.goods.feign.ActionLogFeign;
import com.zsj.lib.utils.ResultData;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public class ActionLogFeignFallback implements ActionLogFeign {

    private Throwable throwable;

    ActionLogFeignFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public ResultData save(@RequestParam Map<String, Object> param) {
        return ResultData.error(501, "save 熔断" + throwable.getMessage());
    }
}
