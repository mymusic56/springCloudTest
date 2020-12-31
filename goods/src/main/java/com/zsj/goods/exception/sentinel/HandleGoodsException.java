package com.zsj.goods.exception.sentinel;

import com.zsj.common.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HandleGoodsException {

    public static String handleInfoException (int goodsId) {
        System.out.println("商品详情接口，流量超过限制！!!!");

        return ResultUtil.json(500, "商品详情接口，流量超过限制！", null);
    }

}
