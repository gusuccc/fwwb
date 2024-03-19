package cn.xstrive.miniprogram.service.openfeign;

import cn.xstrive.entity.*;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "wxinfo-provider-xqj",fallback = FallbackFactory.Default.class)
public interface WxInfoProviderService {
    ///以下关于PicUrl的列表回获取
    @GetMapping("/geturls/{id}")
    public List<PicUrl> geturlsById(@PathVariable("id") String id);
    ///以下关于Comments的列表获取
    @GetMapping("/getcomments/{id}")
    public List<FlatComments> getCommentsbyid(@PathVariable("id") String id);
    //以下关于Flat详细信息的列表获取
    @GetMapping("/flatdetail/{id}")
    public Flat getFlat(@PathVariable("id") String id);
    //根据地址查询所在的房屋列表
    @GetMapping("/getflatbycity/{city}")
    public List<Flat> getFlatbyId(@PathVariable("city") String city);
    //根据openid查用户购物车信息
    @GetMapping("/getcarinfo/{openid}")
    public List<Shopcar>getcarinfo(@PathVariable("openid") String openid);

    @PostMapping("/updatespnum")
    public void updatespnum(@RequestBody Shopcar shopcar);

    @PostMapping("/deletecarinfo")
    public void deletecarinfo(@RequestBody Shopcar shopcar);

    //获取房屋界面的信息
    @GetMapping("/getflatlist")
    public List<Flat> getflatlist();

    @PostMapping("/addwant")
    public void addwant(@RequestBody Shopcar shopcar);
    @PostMapping("/getorderinfo")
    public List<FlatOrder> getorderinfo(@RequestBody String openid);
    @PostMapping("/addorderinfo")
    public void addorderinfo(@RequestBody FlatOrder flatOrder);
    @PostMapping("/updateorder")
    public void updateorder(@RequestBody FlatOrder flatOrder);
    @PostMapping("/detelewant")
    public void detelewant(@RequestBody Shopcar shopcar);
    @GetMapping("/getindex")
    public List<Goods> getindex();

}
