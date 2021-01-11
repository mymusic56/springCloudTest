package com.zsj.lib.client.maindata;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zsj.lib.client.maindata.bean.GoodsSpecsBean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MaindDataApiImpl extends BaseClient implements IMainDataApi {

    @Override
    public List<Map<String, Object>> getGoodsSpecsPageList(int page, int pageSize, Map<String, Object> param) {

        return null;
    }

    @Override
    public GoodsSpecsBean getGoodsSpecsDetail(String goodsCode) {

        try {
            getToken();
            Map<String, String> param = new HashMap<>();
            param.put("pageIndex", "1");
            param.put("pageSize", "10");
            param.put("goodsCode", goodsCode);

            String result = post(configHandler.getMainDataBaseUrl() + this.sepec_page_list_uri, param);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.getInteger("code") == 200) {
                JSONObject data = jsonObject.getJSONObject("data");
                JSONArray list = data.getJSONArray("pageList");
                return list.size() > 0 ? list.getObject(0, GoodsSpecsBean.class) : null;
            }
            throw new Exception((String) jsonObject.getOrDefault("msg", "规格详情获取失败"));
        } catch (MainDataException e) {
            e.printStackTrace();
        } catch (HttpClientErrorException e) {
            e.getResponseBodyAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
