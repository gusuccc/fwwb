package cn.xstrive.userinfo.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;
/*
    用户信息处理器
    自动填充指定属性
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /*
    插入时自动更新时间字段
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill.....");
        this.setFieldValByName("createTime",new Date(),metaObject);          //这里必须为实体属性名才可以！！！
        this.setFieldValByName("ccreateTime",new Date(),metaObject);          //这里必须为实体属性名才可以！！！
    }
    /*
    更新时自动更新时间字段
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill.....");
    }
}
