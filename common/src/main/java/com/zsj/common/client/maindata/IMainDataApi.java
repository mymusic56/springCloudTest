package com.zsj.common.client.maindata;

import com.zsj.common.client.maindata.bean.GoodsSpecsBean;

import java.util.List;
import java.util.Map;

public interface IMainDataApi {
    List<Map<String, Object>> getGoodsSpecsPageList(int page, int pageSize, Map<String, Object> param);

    GoodsSpecsBean getGoodsSpecsDetail(String goodsCode);
}
