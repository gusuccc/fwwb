package cn.xstrive.miniprogram.controller;

import cn.xstrive.entity.Shopcar;
import cn.xstrive.miniprogram.common.RedisOperator;
import cn.xstrive.miniprogram.common.XstriveJSONResult;
import cn.xstrive.miniprogram.pojo.CarSaler;
import cn.xstrive.miniprogram.service.openfeign.WxInfoProviderService;
import cn.xstrive.miniprogram.service.openfeign.UserInfoProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class ShopCarController {
    @Autowired
    RedisOperator redisOperator;
    @Autowired
    WxInfoProviderService partOneInfoService;
    @Autowired
    UserInfoProviderService partTwoService;

    @GetMapping("/getshopcarinfo")
    public XstriveJSONResult getshopcarinfo(@RequestHeader("third-session") String third) {
        if (third == null) {       //说明未登录
            return new XstriveJSONResult(409, "用户未登录！", null);
        }
        boolean isexist = redisOperator.isexist(third);
        if(isexist==false){
            return new XstriveJSONResult(408, "用户不存在！", null);
        }
        String openid = redisOperator.get(third);
        redisOperator.set(third, openid, 24 * 3600);    //每次成功操作了都重置有效期
        List<Shopcar> carinfo = partOneInfoService.getcarinfo(openid);
        List<CarSaler> carSalers=new ArrayList<>();   //对应goodsData
        Set<String>salername = new HashSet<>();       //判断商家是否存在
        for (Shopcar shopcar : carinfo) {
            if(salername.contains(shopcar.getSalername())==false){
                salername.add(shopcar.getSalername());
                CarSaler carSaler = new CarSaler();         //对应goodsData里的对象
                carSaler.setShopname(shopcar.getSalername());
                carSaler.setSelected(false);
                ArrayList<Shopcar> Scl = new ArrayList<>();  //对应goodsInfo
                Scl.add(shopcar);
                carSaler.setGoodsInfo(Scl);
                carSalers.add(carSaler);
            }
            else{
                for (CarSaler c : carSalers) {
                    if(c.getShopname().equals(shopcar.getSalername())){
                        c.getGoodsInfo().add(shopcar);
                        break;
                    }
                }
            }
        }
        return XstriveJSONResult.ok(carSalers);
    }

    @PostMapping("/updatespnum")
    public XstriveJSONResult updatespnum(@RequestHeader("third-session") String third,
                                         @RequestBody Map map){
        if (third == null) {       //说明未登录
            return new XstriveJSONResult(409, "用户未登录！", null);
        }
        boolean isexist = redisOperator.isexist(third);
        if(isexist==false){
            return new XstriveJSONResult(408, "用户不存在！", null);
        }
        Shopcar shopcar = new Shopcar();
        int num= (int) map.get("num");
        String openid = redisOperator.get(third);
        String spid = (String)map.get("spid");
        redisOperator.set(third, openid, 24 * 3600);    //每次成功操作了都重置有效期
        shopcar.setSpnum(num);
        shopcar.setSpid(spid);
        shopcar.setUopenid(openid);
        partOneInfoService.updatespnum(shopcar);
        return XstriveJSONResult.ok("更新成功！");
    }

    @PostMapping("/deleteshopinfo")
    public XstriveJSONResult deleteshopinfo(@RequestHeader("third-session") String third,
                                            @RequestBody Map map){
        if (third == null){
            return new XstriveJSONResult(409, "用户未登录！", null);
        }
        boolean isexist = redisOperator.isexist(third);
        if(isexist==false){
            return new XstriveJSONResult(408, "用户不存在！", null);
        }
        String openid = redisOperator.get(third);

        List<String> goodarr = (List<String>)map.get("goodarr");
        for(int i=0;i<goodarr.size();i++){
            Shopcar shopcar = new Shopcar();
            shopcar.setUopenid(openid);
            shopcar.setSpid(goodarr.get(i));
            partOneInfoService.deletecarinfo(shopcar);
        }
        return XstriveJSONResult.ok("删除成功！");
    }

    @PostMapping("/addwant")
    public XstriveJSONResult addwant(@RequestHeader("third-session") String third,
                                     @RequestBody Map map) throws ParseException {
        if (third == null){
            return new XstriveJSONResult(409, "用户未登录！", null);
        }
        boolean isexist = redisOperator.isexist(third);
        if(isexist==false){
            return new XstriveJSONResult(408, "用户不存在！", null);
        }
        String openid = redisOperator.get(third);
        Shopcar shopcar = new Shopcar();

        String spavatar = (String)map.get("spavatar");
        String spid = (String)map.get("spid");
        String spname = (String)map.get("spname");
        double price = Double.parseDouble(map.get("price").toString());
        String salername = (String)map.get("salername");
        String begintime = (String)map.get("begintime");
        String endtime = (String)map.get("endtime");
        double deposit = Double.parseDouble(map.get("deposit").toString());
        //System.out.println("begintime===>"+begintime);
        //System.out.println("endtime===>"+endtime);
        shopcar.setUopenid(openid);
        shopcar.setSpavatar(spavatar);
        shopcar.setSpid(spid);
        shopcar.setSpnum(1);
        shopcar.setSpname(spname);
        shopcar.setPrice(price);
        shopcar.setSalername(salername);
        /*
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        begintime=year+"/"+begintime;
        endtime=year+"/"+endtime;
        */
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        shopcar.setBegintime(simpleDateFormat.parse(begintime));
        shopcar.setEndtime(simpleDateFormat.parse(endtime));
        shopcar.setDeposit(deposit);
       // System.out.println("shopcar===>"+shopcar);
        partOneInfoService.addwant(shopcar);
        return XstriveJSONResult.ok();
    }
}
