package cn.xstrive.admin.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill.....");
        this.setFieldValByName("signtime",new Date(),metaObject);          //这里必须为实体属性名才可以！！！
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill.....");
    }
}
