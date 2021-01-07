package com.zsj.goods.mapper;

import com.zsj.goods.entity.BrandGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 产品系列 Mapper 接口
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-01
 */
public interface BrandGroupMapper extends BaseMapper<BrandGroup> {
    public int updateDisabled(Integer id, Integer isDisabled, int updatedAt);

    /**
     * @param name
     * @param excludeId
     * @return
     */
    public int getByName(String name, int excludeId);
}
