package com.zsj.lib.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zsj.lib.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MybatisMetaObjectHandlerHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("metaObject_insertFill-------------------------");

        Object createdAt = null;
        Object isDeleted = null;
        Object isDisabled = null;
        if (metaObject.hasGetter("createdAt")) {
            createdAt = getFieldValByName("createdAt", metaObject);
        }
        if (metaObject.hasGetter("isDeleted")) {
            isDeleted = getFieldValByName("isDeleted", metaObject);
        }
        if (metaObject.hasGetter("isDisabled")) {
            isDisabled = getFieldValByName("isDisabled", metaObject);
        }
        if (createdAt == null) {
            this.setFieldValByName("createdAt", DateUtil.getTimestamp(), metaObject);
        }
        if (isDeleted == null) {
            this.setFieldValByName("isDeleted", 0, metaObject);
        }
        if (isDisabled == null) {
            this.setFieldValByName("isDisabled", 0, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("metaObject_updateFill-------------------------");
        Object updatedAt = null;
        if (metaObject.hasGetter("updatedAt")) {
            updatedAt = getFieldValByName("updatedAt", metaObject);
        }
        if (updatedAt == null) {
            this.setFieldValByName("updatedAt", DateUtil.getTimestamp(), metaObject);
        }
    }
}
