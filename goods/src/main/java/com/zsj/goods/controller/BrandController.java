package com.zsj.goods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsj.lib.utils.ResultData;
import com.zsj.lib.utils.ToolUtil;
import com.zsj.goods.entity.Brand;
import com.zsj.goods.mapper.BrandMapper;
import com.zsj.goods.service.impl.BrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 商品品牌 前端控制器
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-01
 */
@RestController
@RequestMapping("/goods/brand")
public class BrandController extends BaseController {
    @Autowired
    private BrandServiceImpl brandService;

    @Resource
    private BrandMapper brandMapper;

    @GetMapping("list")
    public ResultData dataList(@RequestParam Map<String, Object> params) {
        int currentPage = Integer.parseInt(params.getOrDefault("page", 1).toString());
        int pageSize = Integer.parseInt(params.getOrDefault("pageSize", 10).toString());

        QueryWrapper<Brand> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", 0).orderByDesc("ordinal");
        String isDisabled = (String) params.getOrDefault("is_disabled", "");
        if (!isDisabled.equals("")) {
            wrapper.eq("is_disabled", Integer.parseInt(isDisabled));
        }
//        System.out.println(wrapper.getSqlSelect());
//        List<DnBrand> list = brandService.list();
//        IPage<Map<String, Object>> iPage = brandService.pageMaps(new Page<>(currentPage, pageSize), wrapper);
        //3.4.1支持， 3.3.2不支持
        Page<Brand> page = brandService.page(new Page<Brand>(currentPage, pageSize), wrapper);
        page.getRecords().forEach(item -> {
            item.setImgPath(ToolUtil.getRealUrl(item.getImg()));
        });
        Map<String, Object> map = new HashMap<>();

        map.put("currentPage", page.getCurrent());
        map.put("pageTotal", page.getPages());
        map.put("dataTotal", page.getTotal());
        map.put("list", page.getRecords());

        return ResultData.success("操作成功", map);
    }

    @GetMapping("listV2")
    public ResultData dataListV2(@RequestParam Map<String, Object> params) {
        int currentPage = Integer.parseInt(params.getOrDefault("page", 1).toString());
        int pageSize = Integer.parseInt(params.getOrDefault("pageSize", 10).toString());

        QueryWrapper<Brand> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", 0).orderByDesc("ordinal");
        String isDisabled = (String) params.getOrDefault("is_disabled", "");
        if (!isDisabled.equals("")) {
            wrapper.eq("is_disabled", Integer.parseInt(isDisabled));
        }
//        System.out.println(wrapper.getSqlSelect());
//        List<DnBrand> list = brandService.list();
//        IPage<Map<String, Object>> iPage = brandService.pageMaps(new Page<>(currentPage, pageSize), wrapper);
//        IPage<Brand> iPage = brandService.page(new Page<>(currentPage, pageSize), wrapper);

        IPage<Brand> iPage = brandMapper.selectPage(new Page<Brand>(currentPage, pageSize), wrapper);

//        IPage<Map<String, Object>> iPage = brandMapper.selectMapsPage(new Page<Map<String, Object>>(currentPage, pageSize), wrapper);


        Map<String, Object> map = new HashMap<>();
        map.put("currentPage", iPage.getCurrent());
        map.put("pageTotal", iPage.getPages());
        map.put("dataTotal", iPage.getTotal());
        map.put("list", iPage.getRecords());

        return ResultData.success("操作成功", map);
    }

    @PostMapping("delete")
    public ResultData delete(@RequestParam int id) {
        Brand brand = brandService.getById(id);
        if (brand == null) {
            return ResultData.error(200, "已删除成功");
        }
        int flag = brandMapper.deleteById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("flag", flag);
        return ResultData.success("操作成功", map);
    }
}

