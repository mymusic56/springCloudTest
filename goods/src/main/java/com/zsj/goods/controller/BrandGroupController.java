package com.zsj.goods.controller;


import com.zsj.goods.vo.FlagVo;
import com.zsj.goods.vo.PageVo;
import com.zsj.lib.utils.DateUtil;
import com.zsj.lib.utils.ResultData;
import com.zsj.goods.entity.BrandGroup;
import com.zsj.goods.mapper.BrandGroupMapper;
import com.zsj.goods.service.impl.BrandGroupServiceImpl;
import com.zsj.goods.validator.BrandGroupDeleteValidator;
import com.zsj.goods.validator.BrandGroupEditValidator;
import com.zsj.goods.validator.BrandGroupSwitchStateValidator;
import io.swagger.annotations.*;
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
@Api(tags = "产品系列")
public class BrandGroupController extends BaseController {

    @Autowired
    BrandGroupServiceImpl brandGroupService;

    @Resource
    BrandGroupMapper brandGroupMapper;

    @PostMapping(value = "delete")
    @ApiOperation(value = "删除")
    public ResultData<FlagVo> delete(@Valid BrandGroupDeleteValidator validator) throws Exception {
        FlagVo vo = new FlagVo();
        BrandGroup brandGroup = brandGroupService.getById(Integer.parseInt(validator.getId()));
        if (brandGroup == null) {
            vo.setFlag(0);
            return ResultData.success("已删除成功", vo);
        }

        int flag = brandGroupMapper.deleteById(Integer.parseInt(validator.getId()));
        vo.setFlag(flag);
        return ResultData.success("操作成功", vo);
    }

    @PostMapping("switch-state")
    @ApiOperation(value = "状态切换")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", required = true),
            @ApiImplicitParam(name = "isDisabled", value = "状态，1：禁用，0：启用", required = true, allowableValues = "0,1")
    })
    public ResultData<FlagVo> switchState(@Valid BrandGroupSwitchStateValidator validator) {
        FlagVo vo = new FlagVo();
        BrandGroup brandGroup = brandGroupService.getById(Integer.parseInt(validator.getId()));
        if (brandGroup == null) {
            vo.setFlag(-1);
            return ResultData.error(500, "数据不存在！", vo);
        }
        if (brandGroup.getIsDisabled() == Integer.parseInt(validator.getIsDisabled())) {
            vo.setFlag(0);
            return ResultData.success("操作成功", vo);
        }

        brandGroup.setIsDisabled(Integer.parseInt(validator.getIsDisabled()));
        int flag = brandGroupMapper.updateDisabled(brandGroup.getId(), Integer.parseInt(validator.getIsDisabled()), DateUtil.getTimestamp());
        vo.setFlag(flag);
        return ResultData.success("操作成功", vo);
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
    @ApiImplicitParams({
        @ApiImplicitParam(name = "groupName", value = "系列名称", required = false, dataType = "string"),
        @ApiImplicitParam(name = "isDisabled", value = "是否禁用， 0：启用，1：禁用", required = false, dataType = "string"),
        @ApiImplicitParam(name = "page", value = "页数", required = false, dataType = "int"),
        @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, dataType = "int"),
    })
    public ResultData<PageVo<BrandGroup>> dataList(
            @RequestParam(name = "groupName", required = false) String groupName,
            @RequestParam(name = "isDisabled", required = false) String isDisabled,
            @RequestParam(name = "page", defaultValue = "1") String page,
            @RequestParam(name = "pageSize", defaultValue = "10") String pageSize
    ) {
        Map<String, Object> param = new HashMap<>();
        param.put("groupName", groupName);
        if (isDisabled == null || "".equals(isDisabled)) {
            isDisabled = "-1";
        }
        param.put("isDisabled", isDisabled);
        param.put("page", page);
        param.put("pageSize", pageSize);
        return ResultData.success("操作成功", brandGroupService.getList(param));
    }
}

