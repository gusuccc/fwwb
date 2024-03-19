package cn.xstrive.wxinfo.handler;

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
        this.setFieldValByName("focreateTime",new Date(),metaObject);          //这里必须为实体属性名才可以！！！
        this.setFieldValByName("foupdateTime",new Date(),metaObject);

        this.setFieldValByName("fcreateTime",new Date(),metaObject);          //这里必须为实体属性名才可以！！！
        this.setFieldValByName("fupdateTime",new Date(),metaObject);

        this.setFieldValByName("fccreateTime",new Date(),metaObject);          //这里必须为实体属性名才可以！！！
        this.setFieldValByName("fcupdateTime",new Date(),metaObject);

        this.setFieldValByName("carcreateTime",new Date(),metaObject);          //这里必须为实体属性名才可以！！！
        this.setFieldValByName("carupdateTime",new Date(),metaObject);

        this.setFieldValByName("fcgreateTime",new Date(),metaObject);          //这里必须为实体属性名才可以！！！
        this.setFieldValByName("fugpdateTime",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill.....");
        this.setFieldValByName("foupdateTime",new Date(),metaObject);
        this.setFieldValByName("fupdateTime",new Date(),metaObject);
        this.setFieldValByName("fcupdateTime",new Date(),metaObject);
        this.setFieldValByName("carupdateTime",new Date(),metaObject);
        this.setFieldValByName("fugpdateTime",new Date(),metaObject);
    }
}
