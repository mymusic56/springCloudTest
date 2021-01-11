package com.zsj.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Base {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    protected Integer id;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    protected Integer createdAt;

    @TableField(exist = false)
    protected String createdAtStr = "";
}
