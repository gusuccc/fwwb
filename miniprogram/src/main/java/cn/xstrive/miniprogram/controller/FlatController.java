package cn.xstrive.miniprogram.controller;

import cn.xstrive.entity.Flat;
import cn.xstrive.entity.FlatComments;
import cn.xstrive.entity.PicUrl;
import cn.xstrive.entity.Saler;
import cn.xstrive.miniprogram.common.XstriveJSONResult;
import cn.xstrive.miniprogram.pojo.flatinfo;
import cn.xstrive.miniprogram.service.openfeign.WxInfoProviderService;
import cn.xstrive.miniprogram.service.openfeign.UserInfoProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class FlatController {
    @Autowired
    WxInfoProviderService partOneInfoService;
    @Autowired
    UserInfoProviderService partTwoService;
    @GetMapping("/getflatinfo/{id}")
    public XstriveJSONResult getAllinfo(@PathVariable("id") String id){
        Map<String,Object> result=new HashMap<>();
        Flat flat=new Flat();
        Saler saler=new Saler();
        List<FlatComments> list_com=new ArrayList<>();
        List<PicUrl> list_pics=new ArrayList<>();
        flat=partOneInfoService.getFlat(id);
        //System.out.println(flat);
        String saleId = flat.getSaleId();
        saler=partTwoService.getsalerbyid(saleId);
       // System.out.println(saler);
        list_com=partOneInfoService.getCommentsbyid(id);
       // System.out.println(list_com);
        list_pics=partOneInfoService.geturlsById(id);
      //  System.out.println(list_pics);
        result.put("imgurl",list_pics);
        result.put("comments",list_com);
        result.put("flat",flat);
        result.put("salename",saler.getNickName());
        result.put("salerank",saler.getRak());
        return XstriveJSONResult.ok(result);
    }
    @GetMapping("/getflatbycity/{city}")
    public XstriveJSONResult getcitys(@PathVariable("city") String city){
        List<Flat> list=partOneInfoService.getFlatbyId(city);
        Map<String,Object> result=new HashMap<>();
        if(!list.isEmpty()){
        result.put("list",list);
        return XstriveJSONResult.ok(result);}
        else{
            return XstriveJSONResult.build(200,"没有商品",null);
        }
    }
    //还未测试
    @GetMapping("/getflatindexinfo")
    public XstriveJSONResult getflatlist(){
        List<Flat> flatlist = partOneInfoService.getflatlist();
        List<flatinfo> ans=new ArrayList<>();
        int c=0;
        for (Flat ori : flatlist) {
            if(c==8)
                break;
            flatinfo flag=new flatinfo();
            flag.setFlatId(ori.getFlatId());
            flag.setImgurl(ori.getFlatMainpicurl());
            flag.setFlatprice(ori.getFlatPrice());
            flag.setFlatscore(ori.getFlatScore());
            flag.setFlatname(ori.getFlatName());
            flag.setPjnum(35);
            ans.add(flag);
            c++;
        }
        return XstriveJSONResult.ok(ans);
    }
}
