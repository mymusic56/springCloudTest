package com.zsj.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsj.lib.consts.DateFormatConst;
import com.zsj.lib.enums.StatusRemark;
import com.zsj.lib.utils.DateUtil;
import com.zsj.lib.utils.ParamUtil;
import com.zsj.lib.utils.ToolUtil;
import com.zsj.goods.entity.BrandGroup;
import com.zsj.goods.mapper.BrandGroupMapper;
import com.zsj.goods.service.IBrandGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产品系列 服务实现类
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-01
 */
@Service
public class BrandGroupServiceImpl extends ServiceImpl<BrandGroupMapper, BrandGroup> implements IBrandGroupService {

    public Map<String, Object> getList(Map<String, Object> param) {
        int p = ParamUtil.getInt("page", param, 1);
        int pageSize = ParamUtil.getInt("pageSize", param, 10);

        QueryWrapper<BrandGroup> wrapper = new QueryWrapper<BrandGroup>();

        if (param.get("groupName") != null && !param.get("groupName").equals("")) {
            wrapper.like("group_name", param.get("groupName"));
        }

        int isDisabled = ParamUtil.getInt("isDisabled", param, -1);
        if (isDisabled >= 0) {
            wrapper.eq("is_disabled", isDisabled);
        }
        wrapper.orderByDesc("id");
        Page<BrandGroup> page = this.page(new Page<>(p, pageSize), wrapper);
        List<BrandGroup> list = page.getRecords();

        list.forEach(item -> {
            item.setImgPath(ToolUtil.getRealUrl(item.getImg()));
            if (item.getCreatedAt() > 0) {
                item.setCreatedAtStr(DateUtil.getDatetime(item.getCreatedAt(), DateFormatConst.YYYY_MM_DD_HH_MM_SS_EN));
            }
            if (item.getIsDisabled() == StatusRemark.ENABLED.getStatus()) {
                item.setIsDisabledStr(StatusRemark.ENABLED.getRemark());
            } else {
                item.setIsDisabledStr(StatusRemark.DISABLED.getRemark());
            }
        });

        Map<String, Object> map = new HashMap<>();

        map.put("currentPage", page.getCurrent());
        map.put("pageTotal", page.getPages());
        map.put("dataTotal", page.getTotal());
        map.put("list", list);

        return map;
    }
}
