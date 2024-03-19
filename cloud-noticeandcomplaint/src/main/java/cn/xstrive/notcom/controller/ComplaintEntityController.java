package cn.xstrive.notcom.controller;

import cn.xstrive.entity.ComplaintEntity;
import cn.xstrive.entity.Customer;
import cn.xstrive.notcom.service.ComplaintEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
    投诉信息管理服务控制器类
    得到的数据均以JSON 字符串形式返回
 */
@RestController
public class ComplaintEntityController {

    @Autowired
    ComplaintEntityService complaintEntityService;
    /*
    获取投诉列表
     */
    @GetMapping("/ComplaintEntity/list")
    public List<ComplaintEntity> ComplaintEntitylist(){
        List<ComplaintEntity> list= complaintEntityService.list(null);
        return list;
    }
    /*
    根据客户端发出请求获取的id删除指定投诉信息
     */
    @GetMapping("/ComplaintEntity/{id}")
    public void moveComplaintEntity(@PathVariable("id") Long id){
        System.out.println("要删除的id是:"+id);
        complaintEntityService.removeById(id);
    }
    /*
    接收前端post请求中的请求体
    更新对应投诉信息
     */
    @PostMapping("/upComplaintEntity")
    public void updateCEbyid(@RequestBody ComplaintEntity complaintEntity){
        complaintEntityService.updateById(complaintEntity);
    }
    /*
    接收前端post请求中的请求体
    增加投诉信息
     */
    @PostMapping("/addComplaintEntity")
    public void addComplaintEntity(@RequestBody ComplaintEntity complaintEntity){
        complaintEntityService.save(complaintEntity);
    }
}
