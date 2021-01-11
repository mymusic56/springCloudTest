package com.zsj.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zsj.common.entity.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 操作日志
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dn_action_log")
public class ActionLog extends Base {

    private static final long serialVersionUID = 1L;

    /**
     * 模块id
     */
    private Integer moduleId;

    /**
     * 行为id
     */
    private Integer actionId;

    /**
     * 用户id
     */
    private Integer accountId;

    /**
     * ip
     */
    private String ip;

    /**
     * 模块名
     */
    private String moduleName;

    /**
     * 行为名
     */
    private String actionName;

    /**
     * 资源id或编号
     */
    private String resourceSn;

    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 详细内容
     */
    private String content;


}
