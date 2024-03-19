package cn.xstrive.miniprogram.controller;

import cn.xstrive.miniprogram.common.RedisOperator;
import cn.xstrive.miniprogram.common.XstriveJSONResult;
import cn.xstrive.miniprogram.service.openfeign.WxInfoProviderService;
import cn.xstrive.miniprogram.util.UniqueId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private RedisOperator redis;
    @Autowired
    private WxInfoProviderService pinfo;
    @GetMapping("/redistest")
    public String redistest(){
        redis.set("test","hello redis!");

        return redis.get("test");
    }
    @GetMapping("/promisetest")
    public XstriveJSONResult promisetest(@RequestHeader(value = "app-id",required = false) String appid,
                                         @RequestHeader(value = "third-session",required = false) String thirds,
                                         @RequestParam(value = "data",required = false) String data){
        System.out.println("app-id=="+appid);
        System.out.println("third-session=="+thirds);
        System.out.println("data=="+data);
        System.out.println("promise test!");
        String s = redis.get(thirds);
        if(redis.isexist(thirds)==true){
            System.out.println("你可以获取信息了！");
            System.out.println("你的openid为:"+redis.get((thirds)));
        }
        else{
            System.out.println("未登录，无法获取信息！");
        }
        return XstriveJSONResult.ok("测试成功！");
    }
    @GetMapping("/idtest")
    public String idtest(){
        return UniqueId.getuniqueid();
    }


}
