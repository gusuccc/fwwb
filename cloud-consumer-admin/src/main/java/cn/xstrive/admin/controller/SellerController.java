package cn.xstrive.admin.controller;

import cn.xstrive.admin.service.openfeign.ConsumerInfoService;
import cn.xstrive.entity.Customer;
import cn.xstrive.entity.Saler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SellerController {
    @Autowired
    ConsumerInfoService consumerInfoService;
    @GetMapping("/seller")
    public String toseler(Model model){
        //获取数据逻辑
        List<Saler> salers = consumerInfoService.salerList();
        model.addAttribute("lists",salers);
        return "supplier";
    }
    //增加商家
    @PostMapping("/saler/add")
    public String addsaler(Saler saler,@RequestParam("province") String province,@RequestParam("city") String city,
                           @RequestParam("county") String county,@RequestParam("detailaddr") String detailaddr){
        saler.setAddress(province+city+county+detailaddr);
        consumerInfoService.addsaler(saler);
        return "redirect:/seller";
    }
    //更新商家信息
    @PostMapping("/saler/update")
    public String updatebyid(Saler saler, @RequestParam("province") String province, @RequestParam("city") String city,
                             @RequestParam("county") String county, @RequestParam("detailaddr") String detailaddr, @RequestParam("orignaddr") String orignaddr){
        if(province!=""&&city!=""&&county!=""&&detailaddr!="")          //都非空的时候才允许组成新地址
            saler.setAddress(province+city+county+detailaddr);
        else
            saler.setAddress(orignaddr);
        consumerInfoService.upsalerbyid(saler);
        return "redirect:/seller";
    }
}
