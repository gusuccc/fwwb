package cn.xstrive.miniprogram.controller;

import cn.xstrive.entity.FlatOrder;
import cn.xstrive.entity.Shopcar;
import cn.xstrive.miniprogram.common.RedisOperator;
import cn.xstrive.miniprogram.common.XstriveJSONResult;
import cn.xstrive.miniprogram.pojo.OrderEntity;
import cn.xstrive.miniprogram.service.openfeign.UserInfoProviderService;
import cn.xstrive.miniprogram.service.openfeign.WxInfoProviderService;
import cn.xstrive.miniprogram.util.UniqueId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

@RestController
public class OrderController {

    @Autowired
    RedisOperator redisOperator;
    @Autowired
    WxInfoProviderService partOneInfoService;
    @Autowired
    UserInfoProviderService userInfoProviderService;

    @GetMapping("/getorderinfo")
    public XstriveJSONResult getorderinfo(@RequestHeader("third-session") String third){
        if (third == null){
            return new XstriveJSONResult(409, "用户未登录！", null);
        }
        boolean isexist = redisOperator.isexist(third);
        if(isexist==false){
            return new XstriveJSONResult(408, "用户不存在！", null);
        }
        String openid = redisOperator.get(third);
        redisOperator.set(third, openid, 24 * 3600);    //每次成功操作了都重置有效期

        List<FlatOrder> orderList = partOneInfoService.getorderinfo(openid);
        ArrayList<OrderEntity> orderEntities = new ArrayList<>();
        for (FlatOrder flatOrder : orderList) {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderid(flatOrder.getOrderid());
            orderEntity.setOrderstatus(flatOrder.getOrderstatus());
            orderEntity.setAdd_time(flatOrder.getFocreateTime());
            orderEntity.setActual_price(flatOrder.getUnitPrice()*flatOrder.getDaynum()-flatOrder.getDeductionMoney());
            orderEntity.setFreight_price(10);
            orderEntity.setSpid(flatOrder.getFlatid());
            orderEntity.setSpurl(flatOrder.getSpurl());
            orderEntity.setSpname(flatOrder.getFlatname());
            orderEntities.add(orderEntity);
        }
        return XstriveJSONResult.ok(orderEntities);
    }

    @PostMapping("/addorder")
    public XstriveJSONResult addorder(@RequestHeader("third-session") String third,
                                      @RequestBody Map map) throws ParseException {
        if (third == null){
            return new XstriveJSONResult(409, "用户未登录！", null);
        }
        boolean isexist = redisOperator.isexist(third);
        if(isexist==false){
            return new XstriveJSONResult(408, "用户不存在！", null);
        }
        String openid = redisOperator.get(third);
        redisOperator.set(third, openid, 24 * 3600);    //每次成功操作了都重置有效期

        String spid = (String)map.get("spid");
        String spname = (String)map.get("spname");
        String begindata = (String)map.get("begindata");
        String enddata = (String)map.get("enddata");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String deposit = (String)map.get("deposit");
        String unitprice = (String)map.get("unitprice");
        int daynum = (int)map.get("daynum");
        int decmoney = (int)map.get("decmoney");
        String spurl = (String)map.get("spurl");
        FlatOrder flatOrder = new FlatOrder();
        String uniqueid = UniqueId.getuniqueid();
        flatOrder.setOrderid(uniqueid);
        flatOrder.setFlatid(spid);
        flatOrder.setFlatname(spname);
        flatOrder.setBegindata(simpleDateFormat.parse(begindata));
        flatOrder.setEnddata(simpleDateFormat.parse(enddata));
        flatOrder.setBelongedHostid("1001");
        flatOrder.setHirepersonid(openid);
        flatOrder.setContactwayHire(userInfoProviderService.getuseroneinfo(openid).getTelephone());
        flatOrder.setDeposit(Long.parseLong(deposit));
        flatOrder.setUnitPrice(Long.parseLong(unitprice));
        flatOrder.setDaynum(daynum);
        flatOrder.setDeductionMoney(decmoney);
        flatOrder.setOrderstatus("待付款");
        flatOrder.setSpurl(spurl);

        Shopcar shopcar = new Shopcar();
        shopcar.setUopenid(openid);
        shopcar.setSpid(spid);
        partOneInfoService.deletecarinfo(shopcar);
        partOneInfoService.addorderinfo(flatOrder);
      //  System.out.println("flatorder==>"+flatOrder);
        HashMap<String, Object> result = new HashMap<>();
        result.put("openid",uniqueid);
        return XstriveJSONResult.ok(result);
    }
    @PostMapping("/updateorder")
    public XstriveJSONResult updateorder(@RequestHeader("third-session") String third,
                                         @RequestBody Map map){
        if (third == null){
            return new XstriveJSONResult(409, "用户未登录！", null);
        }
        boolean isexist = redisOperator.isexist(third);
        if(isexist==false){
            return new XstriveJSONResult(408, "用户不存在！", null);
        }
        String openid = redisOperator.get(third);
        redisOperator.set(third, openid, 24 * 3600);    //每次成功操作了都重置有效期
        String orderid =(String)map.get("orderid");
        FlatOrder flatOrder = new FlatOrder();
        flatOrder.setOrderid(orderid);
        flatOrder.setOrderstatus("待发货");
        //System.out.println("flatorder===>"+flatOrder);
        partOneInfoService.updateorder(flatOrder);
        return XstriveJSONResult.ok();
    }
}
