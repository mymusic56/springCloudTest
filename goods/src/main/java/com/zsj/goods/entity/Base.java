package com.zsj.goods.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Base {
    /**
     * 主键ID
     */
    protected Integer id;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    protected Integer createdAt;
}
