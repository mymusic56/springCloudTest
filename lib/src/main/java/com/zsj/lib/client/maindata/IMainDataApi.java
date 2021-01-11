package com.zsj.lib.client.maindata;

import com.zsj.lib.client.maindata.bean.GoodsSpecsBean;

import java.util.List;
import java.util.Map;

public interface IMainDataApi {
    List<Map<String, Object>> getGoodsSpecsPageList(int page, int pageSize, Map<String, Object> param);

    GoodsSpecsBean getGoodsSpecsDetail(String goodsCode);
}
