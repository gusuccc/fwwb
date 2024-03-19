package cn.xstrive.userinfo.controller;

import cn.xstrive.entity.Customer;
import cn.xstrive.userinfo.mapper.CustomerMapper;
import cn.xstrive.userinfo.service.CustomerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MiniProgramController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/wxisexist")
    public Customer wxisexist(@RequestParam(value = "openid",required = false) String opneid){
        QueryWrapper<Customer> wrapper = new QueryWrapper<>();
        QueryWrapper<Customer> eq = wrapper.eq("openid", opneid);
        Customer one = customerService.getOne(eq);       //这样写就可以查到数据
        return one;    //如果为空就会返回null，说明不存在
    }
    @PostMapping("/savainfo")
    public void saveinfo(@RequestBody Customer customer){
        customerService.save(customer);
    }

}
