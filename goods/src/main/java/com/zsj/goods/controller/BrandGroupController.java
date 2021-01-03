package com.zsj.goods.controller;


import com.zsj.common.utils.ParamUtil;
import com.zsj.common.utils.ResultData;
import com.zsj.goods.entity.BrandGroup;
import com.zsj.goods.mapper.BrandGroupMapper;
import com.zsj.goods.service.impl.BrandGroupServiceImpl;
import com.zsj.goods.validator.BrandGroupDeleteValidator;
import com.zsj.goods.validator.BrandGroupSwitchStateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 产品系列 前端控制器
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-01
 */
@RestController
@RequestMapping("/goods/brand-group")
public class BrandGroupController extends BaseController {

    @Autowired
    BrandGroupServiceImpl brandGroupService;

    @Resource
    BrandGroupMapper brandGroupMapper;

    @PostMapping("delete")
    public ResultData delete(@Valid BrandGroupDeleteValidator validator) throws Exception {
        BrandGroup brandGroup = brandGroupService.getById(validator.getId());
        if (brandGroup == null) {
            return ResultData.success("已删除成功");
        }

        int flag = brandGroupMapper.deleteById(validator.getId());
        Map<String, Object> map = new HashMap<>();
        map.put("flag", flag);
        return ResultData.success("操作成功", map);
    }

    @PostMapping("switch-state")
    public ResultData switchState(@Valid BrandGroupSwitchStateValidator validator) {
        BrandGroup brandGroup = brandGroupService.getById(validator.getId());
        if (brandGroup == null) {
            return ResultData.error(500, "数据不存在！");
        }

        if (brandGroup.getIsDisabled() == 1) {
            return ResultData.success("操作成功");
        }

        brandGroup.setIsDisabled(validator.getIsDisabled());
        int flag = brandGroupMapper.updateById(brandGroup);

        Map<String, Object> map = new HashMap<>();
        map.put("flag", flag);
        return ResultData.success("操作成功", map);
    }

    @PostMapping("update")
    @Validated
    public ResultData update(@RequestParam Map<String, Object> param) {
        int id = ParamUtil.getInt("id", param, 0);
        BrandGroup brandGroup = brandGroupService.getById(id);
        if (brandGroup == null || brandGroup.getIsDeleted() == 1) {
            return ResultData.error(500, "数据不存在！");
        }
//        brandGroup.
        int flag = brandGroupMapper.updateById(brandGroup);
        Map<String, Object> map = new HashMap<>();
        map.put("flag", flag);
        return ResultData.success("操作成功", map);
    }
}

