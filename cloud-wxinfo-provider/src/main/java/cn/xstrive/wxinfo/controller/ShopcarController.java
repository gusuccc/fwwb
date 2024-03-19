package cn.xstrive.wxinfo.controller;

import cn.xstrive.entity.Shopcar;
import cn.xstrive.wxinfo.service.ShopcarService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShopcarController {
    @Autowired
    ShopcarService shopcarService;

    @GetMapping("/getcarinfo/{openid}")
    public List<Shopcar>getcarinfo(@PathVariable("openid") String openid){
        Wrapper uopenid = new QueryWrapper<Shopcar>().eq("uopenid", openid);

        return shopcarService.list(uopenid);
    }

    @PostMapping("/updatespnum")
    public void updatespnum(@RequestBody Shopcar shopcar){
        UpdateWrapper<Shopcar> UpdateWrapper = new UpdateWrapper<>();
        UpdateWrapper<Shopcar> and = UpdateWrapper.and(wrapper -> wrapper.eq("uopenid", shopcar.getUopenid()).eq("spid", shopcar.getSpid()));
        shopcarService.update(shopcar,and);
    }

    @PostMapping("/deletecarinfo")
    public void deletecarinfo(@RequestBody Shopcar shopcar){
        QueryWrapper<Shopcar> Wrapper = new QueryWrapper<>();
        Wrapper.and(wrapper->wrapper.eq("uopenid", shopcar.getUopenid()).eq("spid", shopcar.getSpid()));
        shopcarService.remove(Wrapper);
    }
    @PostMapping("/addwant")
    public void addwant(@RequestBody Shopcar shopcar){
        shopcarService.save(shopcar);
    }

    @PostMapping("/detelewant")
    public void detelewant(@RequestBody Shopcar shopcar){
        QueryWrapper<Shopcar> wrapper = new QueryWrapper<Shopcar>().eq("spid", shopcar.getSpid());
        shopcarService.remove(wrapper);
    }


}
