package cn.xstrive.userinfo.controller;


import cn.xstrive.entity.Customer;
import cn.xstrive.entity.Saler;
import cn.xstrive.userinfo.mapper.SalerMapper;
import cn.xstrive.userinfo.service.SalerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商家信息表 前端控制器
 * </p>
 *
 * @author xqj
 * @since 2021-02-17
 */
@RestController
public class SalerController {
    @Autowired
    SalerService salerService;
    @GetMapping("/test1")             //测试成功！！
    public Saler test1(){
        Saler saler = new Saler();
        saler.setUsername("fag");
        saler.setNickName("fgga");
        saler.setCertInfo("xxxxxx");
        salerService.save(saler);
        return  saler;
    }
    //==================================以下是业务代码
    /*
    获取商家列表
     */
    @GetMapping("/saler/list")
    public List<Saler> salerList(){
        List<Saler> list = salerService.list(null);
        return list;
    }
    /*
    根据客户端发出请求获取的id删除指定商家信息
     */
    @PostMapping("/addsaler")
    public void addsaler(@RequestBody Saler saler){
        salerService.save(saler);
    }
    /*
   接收前端post请求中的请求体
   更新对应商家信息
    */
    @PostMapping("/upsaler")
    public void upsalerbyid(@RequestBody Saler saler){
        //System.out.println("更新saler方法被调用了"+saler);
        boolean b = salerService.updateById(saler);
        //System.out.println("b===>"+b);
    }
    /*
    根据id获取指定商家信息
     */
    @GetMapping("/getsalerbyid/{id}")
    public Saler getsalerbyid(@PathVariable("id") String id){
        Saler saler=new Saler();
        saler=salerService.getOne(new QueryWrapper<Saler>().eq("salerid",id));
        return saler;
    }
}

