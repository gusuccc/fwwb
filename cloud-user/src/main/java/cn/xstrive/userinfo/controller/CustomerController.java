package cn.xstrive.userinfo.controller;


import cn.xstrive.entity.Customer;
import cn.xstrive.entity.Shopcar;
import cn.xstrive.userinfo.mapper.CustomerMapper;
import cn.xstrive.userinfo.service.CustomerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 顾客信息表 前端控制器
 * </p>
 *
 * @author xqj
 * @since 2021-02-17
 */
@RestController
public class CustomerController {

    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    CustomerService customerService;
    /*
    测试请求
     */
    @GetMapping("/customer/test1")              //测试成功！
    public Customer test(){
        Customer customer = customerMapper.selectOne(new QueryWrapper<Customer>().eq("username", "xqj"));
        return customer;
    }

    //下面的是业务逻辑的代码
     /*
    获取顾客列表
     */
    @GetMapping("/customer/list")
    public List<Customer> list(){
        List<Customer> list = customerService.list(null);
        return list;
    }
    /*
    根据客户端发出请求获取的id删除指定顾客信息
     */
    @GetMapping("/consumer/move/{id}")
    public boolean moveconsumer(@PathVariable("id") Long id){
     //   System.out.println("要删除的id是:"+id);
        boolean b = customerService.removeById(id);
        return b;
    }
    /*
   接收前端post请求中的请求体
   更新对应顾客信息
    */
    @PostMapping("/upconsumer")
    public void updatebyid(@RequestBody Customer customer){
        customerService.updateById(customer);
    }
    /*
   接收前端post请求中的请求体
   增加对应顾客信息
    */
    @PostMapping("/addconsumer")
    public void addconsumer(@RequestBody Customer customer){
        customerService.save(customer);
    }
    /*
    接收前端post请求中的请求体
    更新顾客信息
     */
    @PostMapping("/updateaddrinfo")
    public void updateaddrinfo(@RequestBody Customer customer){
        UpdateWrapper<Customer> UpdateWrapper = new UpdateWrapper<>();
        UpdateWrapper.eq("openid",customer.getOpenid());
        customerService.update(customer,UpdateWrapper);
    }

    @PostMapping("/getaddrinfo")
    public Customer getaddrinfo(@RequestBody Customer customer){
       // System.out.println(customer);
        Customer customer1=customerService.getOne(new QueryWrapper<Customer>().eq("openid",customer.getOpenid()));
       // System.out.println(customer1);
        return customer1;
    }
    @PostMapping
    public Long getmoneynum(@RequestBody String opneid){
        Customer customer1=customerService.getOne(new QueryWrapper<Customer>().eq("openid",opneid));
        return customer1.getMoneynum();
    }
    @PostMapping("/rechargemoney")
    public void rechargemoney(@RequestBody Customer customer){
        Customer customer1=customerService.getOne(new QueryWrapper<Customer>().eq("openid",customer.getOpenid()));
        UpdateWrapper<Customer> UpdateWrapper = new UpdateWrapper<>();

        customer.setMoneynum(customer.getMoneynum()+customer1.getMoneynum());
        UpdateWrapper.eq("openid",customer.getOpenid());
        customerService.update(customer,UpdateWrapper);
    }

    @PostMapping("/getuseroneinfo")
    public Customer getuseroneinfo(@RequestBody String openid){
        QueryWrapper<Customer> openid1 = new QueryWrapper<Customer>().eq("openid", openid);
        return customerService.getOne(openid1);
    }
}

