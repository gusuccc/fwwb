package cn.xstrive.admin.controller;

import cn.xstrive.admin.service.openfeign.ConsumerInfoService;
import cn.xstrive.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ConsumerController {
    @Autowired
    ConsumerInfoService consumerInfoService;
    @GetMapping("/consumer")
    public String toconsumer(Model model){
        List<Customer> list = consumerInfoService.list();
        model.addAttribute("clists",list);
        return "consumer";
    }
    @ResponseBody
    @GetMapping("/consumer/move/{id}")
    public String moveconsumer(@PathVariable("id") Long id){
        boolean isdelete = consumerInfoService.moveconsumer(id);
        return "删除成功!";
    }

    //更新用户信息
    @PostMapping("/consumer/update")
    public String updatebyid(Customer customer, @RequestParam("province") String province,@RequestParam("city") String city,
                             @RequestParam("county") String county,@RequestParam("detailaddr") String detailaddr,@RequestParam("orignaddr") String orignaddr){
        if(province!=""&&city!=""&&county!=""&&detailaddr!="")          //都非空的时候才允许组成新地址
            customer.setAddress(province+city+county+detailaddr);
        else
            customer.setAddress(orignaddr);
        consumerInfoService.updatebyid(customer);       //当对象中的字段为空的时候，是不会进行更新的
        return "redirect:/consumer";
    }
    //添加用户
    @PostMapping("/consumer/add")
    public String addconsumer(Customer customer,@RequestParam("province") String province,@RequestParam("city") String city,
                              @RequestParam("county") String county,@RequestParam("detailaddr") String detailaddr){
        //System.out.println(province+city+county+detailaddr);
        /*customer.setAddress(province+city+county+detailaddr);
        System.out.println("======>"+customer);*/
        consumerInfoService.addconsumer(customer);
        return "redirect:/consumer";
    }

}
