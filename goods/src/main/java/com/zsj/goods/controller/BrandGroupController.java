package com.zsj.goods.controller;


import com.zsj.common.utils.DateUtil;
import com.zsj.common.utils.ResultData;
import com.zsj.goods.entity.BrandGroup;
import com.zsj.goods.mapper.BrandGroupMapper;
import com.zsj.goods.service.impl.BrandGroupServiceImpl;
import com.zsj.goods.validator.BrandGroupDeleteValidator;
import com.zsj.goods.validator.BrandGroupEditValidator;
import com.zsj.goods.validator.BrandGroupSwitchStateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
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
        BrandGroup brandGroup = brandGroupService.getById(Integer.parseInt(validator.getId()));
        if (brandGroup == null) {
            return ResultData.success("已删除成功");
        }

        int flag = brandGroupMapper.deleteById(Integer.parseInt(validator.getId()));
        Map<String, Object> map = new HashMap<>();
        map.put("flag", flag);
        return ResultData.success("操作成功", map);
    }

    @PostMapping("switch-state")
    public ResultData switchState(@Valid BrandGroupSwitchStateValidator validator) {
        BrandGroup brandGroup = brandGroupService.getById(Integer.parseInt(validator.getId()));
        if (brandGroup == null) {
            return ResultData.error(500, "数据不存在！");
        }
        if (brandGroup.getIsDisabled() == Integer.parseInt(validator.getIsDisabled())) {
            return ResultData.success("操作成功");
        }

        brandGroup.setIsDisabled(Integer.parseInt(validator.getIsDisabled()));
        int flag = brandGroupMapper.updateDisabled(brandGroup.getId(), Integer.parseInt(validator.getIsDisabled()), DateUtil.getTimestamp());

        Map<String, Object> map = new HashMap<>();
        map.put("flag", flag);
        return ResultData.success("操作成功", map);
    }

    @PostMapping("edit")
    public ResultData edit(@Valid BrandGroupEditValidator validator) {
        int id = Integer.parseInt(validator.getId());
        BrandGroup brandGroup = null;
        int flag = 0;

        //判断系列名称是否重复
        int count = brandGroupMapper.getByName(validator.getGroupName(), id);
        if (count >= 1) {
            return ResultData.error(500, "系列名称已存在！");
        }
        if (id > 0) {
            brandGroup = brandGroupService.getById(id);
            if (brandGroup == null || brandGroup.getIsDeleted() == 1) {
                return ResultData.error(500, "数据不存在！");
            }
            brandGroup.setImg(validator.getImg());
            brandGroup.setGroupName(validator.getGroupName());
            brandGroup.setOrdinal(Integer.parseInt(validator.getOrdinal()));
            flag = brandGroupMapper.updateById(brandGroup);
        } else {
            brandGroup = new BrandGroup();
            brandGroup.setImg(validator.getImg());
            brandGroup.setGroupName(validator.getGroupName());
            brandGroup.setOrdinal(Integer.parseInt(validator.getOrdinal()));
            flag = brandGroupMapper.insert(brandGroup);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("flag", flag);
        return ResultData.success("操作成功", map);
    }

    @GetMapping("list")
    public ResultData dataList(@RequestParam Map<String, Object> param) {
        Map<String, Object> map = brandGroupService.getList(param);
        return ResultData.success("操作成功", map);
    }
}

