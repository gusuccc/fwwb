package cn.xstrive.admin.controller;

import cn.xstrive.admin.service.openfeign.ConsumerInfoService;
import cn.xstrive.admin.service.openfeign.NoticeComplainService;
import cn.xstrive.entity.ComplaintEntity;
import cn.xstrive.entity.Customer;
import cn.xstrive.entity.SysNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
/*
返回投诉以及公告信息，通过Model传给前端，用于数据渲染
 */
@Controller
public class ComplainController {
    @Autowired
    NoticeComplainService noticeComplainService;

    @GetMapping("/complain")
    public String tocomplain(Model model){
        List<ComplaintEntity> list = noticeComplainService.ComplaintEntitylist();
        model.addAttribute("comlists",list);   //将公告列表加入Model中
        return "complain";
    }
    @GetMapping("/complain/move/{id}")          //删除用户投诉信息
    public String deletecomplain(@PathVariable("id") Long id){
        System.out.println("要删除的id是"+id);
        noticeComplainService.moveComplaintEntity(id);
        return "redirect:/complain";
    }
    @PostMapping("/complain/update")   //更新用户投诉信息
    public String updatebyid(ComplaintEntity complaintEntity){

        System.out.println(complaintEntity);
        noticeComplainService.updateCEbyid(complaintEntity);   //当对象中的字段为空的时候，是不会进行更新的
        return "redirect:/complain";
    }
    @PostMapping("/complain/add")     //增加用户投诉信息
    public String addconsumer(ComplaintEntity complaintEntity){
        //System.out.println(sysNotice);
        noticeComplainService.addComplaintEntity(complaintEntity);
        return "redirect:/complain";
    }
}
