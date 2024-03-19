package cn.xstrive.miniprogram.controller;

import cn.xstrive.entity.Customer;
import cn.xstrive.miniprogram.common.RedisOperator;
import cn.xstrive.miniprogram.common.XstriveJSONResult;
import cn.xstrive.miniprogram.service.openfeign.UserInfoProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomerController {
    @Autowired
    UserInfoProviderService userInfoProviderService;
    @Autowired
    RedisOperator redisOperator;
    @PostMapping("/updateaddrinfo")
    public XstriveJSONResult updateaddrinfo(@RequestHeader("third-session") String third,
                                            @RequestBody Map map){
        if (third == null) {       //说明未登录
            return new XstriveJSONResult(409, "用户未登录！", null);
        }
        boolean isexist = redisOperator.isexist(third);
        if(isexist==false){
            return new XstriveJSONResult(408, "用户不存在！", null);
        }
        String address = (String)map.get("address");
        String mobile = (String)map.get("mobile");
        String name = (String)map.get("name");
        Customer customer = new Customer();
        String openid = redisOperator.get(third);
        customer.setOpenid(openid);
        customer.setUsername(name);
        customer.setAddress(address);
        customer.setTelephone(mobile);
        userInfoProviderService.updateaddrinfo(customer);
        return XstriveJSONResult.ok("更新地址成功！");
    }

    @GetMapping("/getaddrinfo")
    public XstriveJSONResult getaddrinfo(@RequestHeader("third-session") String third){
        if (third == null) {       //说明未登录
            return new XstriveJSONResult(409, "用户未登录！", null);
        }
        boolean isexist = redisOperator.isexist(third);
        if(isexist==false){
            return new XstriveJSONResult(408, "用户不存在！", null);
        }
        String openid = redisOperator.get(third);
        Customer customer = new Customer();
        customer.setOpenid(openid);
       // System.out.println(customer);
        Customer customer1=userInfoProviderService.getaddrinfo(customer);
       // System.out.println(customer1);
        Map<String,Object> result=new HashMap<>();
        String alladdress = customer1.getAddress();
        //result.put("address",customer.getAddress());
        if(alladdress==null){
            return new XstriveJSONResult(200,"无地址",null);
        }
        String[] split = alladdress.split(",");
        String full_region=split[0];
        String address=split[1];
        result.put("mobile",customer1.getTelephone());
        result.put("full_region",full_region);
        result.put("address",address);
        result.put("name",customer1.getUsername());
        return XstriveJSONResult.ok(result);
    }

    @GetMapping("/getrestmoney")
    public XstriveJSONResult getrestmoney(@RequestHeader("third-session") String third){
        if (third == null) {       //说明未登录
            return new XstriveJSONResult(409, "用户未登录！", null);
        }
        boolean isexist = redisOperator.isexist(third);
        if(isexist==false){
            return new XstriveJSONResult(408, "用户不存在！", null);
        }
        //System.out.println("third==>"+third);
        String openid = redisOperator.get(third);
        Long restmoney = userInfoProviderService.getmoneynum(openid);
        //System.out.println("restmoney===>"+restmoney);
        Map<String,Object> result=new HashMap<>();
        result.put("restmoney",restmoney);
        return XstriveJSONResult.ok(result);
    }
    @PostMapping("/rechargemoney")
    public XstriveJSONResult rechargemoney(@RequestHeader("third-session") String third,
                                           @RequestBody Map map){
        if (third == null) {       //说明未登录
            return new XstriveJSONResult(409, "用户未登录！", null);
        }
        boolean isexist = redisOperator.isexist(third);
        if(isexist==false){
            return new XstriveJSONResult(408, "用户不存在！", null);
        }
        String openid = redisOperator.get(third);
        String money = (String)map.get("Rechargenum");
        long l = Long.parseLong(money);
        //System.out.println("money===>"+money);
        Customer customer = new Customer();
        customer.setOpenid(openid);
        customer.setMoneynum(l);
        userInfoProviderService.rechargemoney(customer);
        return XstriveJSONResult.ok();
    }
}
