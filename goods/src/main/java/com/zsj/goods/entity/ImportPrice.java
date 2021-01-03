package com.zsj.goods.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zsj.goods.entity.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品价格导入
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dn_import_price")
public class ImportPrice extends Base {

    private static final long serialVersionUID=1L;

    /**
     * 任务编号
     */
    private String taskNo;

    /**
     * 记录状态 1：无效记录 2：待处理 3：已处理
     */
    private Boolean statu;

    /**
     * 记录类型 0：基础价格
     */
    private Boolean type;

    /**
     * 记录数量
     */
    private Integer quantity;

    /**
     * 有效条数，不含重复的记录
     */
    private Integer valid;

    /**
     * 执行条数
     */
    private Integer execNum;

    /**
     * 执行时间（系统在指定时间执行）
     */
    private Integer executedAt;

    /**
     * 更新时间
     */
    private Integer updatedAt;

    /**
     * 管理员ID
     */
    private Integer userId;

    /**
     * 0正常1删除
     */
    private Integer isDeleted;


}
