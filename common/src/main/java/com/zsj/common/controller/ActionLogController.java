package com.zsj.common.controller;


import com.zsj.common.entity.ActionLog;
import com.zsj.common.service.IActionLogService;
import com.zsj.lib.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.zsj.common.controller.BaseController;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 操作日志 前端控制器
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-11
 */
@RestController
@RequestMapping("/common/action-log")
public class ActionLogController extends BaseController {
    @Autowired
    IActionLogService actionLogService;

    @PostMapping("save")
    public ResultData save(@RequestParam Map<String, Object> param) {
        String uri = (String) param.get("uri");
        if (uri == null) {
            return ResultData.error(500, "日志保存缺少URI参数！");
        }
        ActionLog log = new ActionLog();
        log.setAccountId(Integer.parseInt((String) param.get("accountId")));
        log.setActionId(Integer.parseInt((String) param.get("actionId")));

        //访问方法
        log.setModuleName((String) param.get("moduleName"));
        log.setModuleId(1);
        log.setActionName((String) param.get("actionName"));

        log.setContent((String) param.get("content"));
        log.setIp((String) param.get("ip"));
        log.setResourceSn((String) param.get("resourceSn"));
        log.setUsername((String) param.get("username"));
        actionLogService.save(log);
        return ResultData.success("", log);
    }
}

