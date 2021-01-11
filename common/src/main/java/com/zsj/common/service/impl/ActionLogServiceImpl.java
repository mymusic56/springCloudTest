package com.zsj.common.service.impl;

import com.zsj.common.entity.ActionLog;
import com.zsj.common.mapper.ActionLogMapper;
import com.zsj.common.service.IActionLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志 服务实现类
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-11
 */
@Service
public class ActionLogServiceImpl extends ServiceImpl<ActionLogMapper, ActionLog> implements IActionLogService {

}
