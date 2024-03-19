package cn.xstrive.admin.controller;

import cn.xstrive.admin.service.openfeign.ConsumerInfoService;
import cn.xstrive.admin.service.openfeign.NoticeComplainService;
import cn.xstrive.entity.Customer;
import cn.xstrive.entity.SysNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
public class NoticeController {
    @Autowired
    NoticeComplainService noticeComplainService;
    @GetMapping("/notice")
    public String tonotice(Model model){
        List<SysNotice> list = noticeComplainService.SysNoticelist();
        model.addAttribute("noticelists",list);   //讲公告信息返回给前端
        return "announcement";
    }
    //移除用户公告信息
    @GetMapping("/notice/move/{id}")
    public String deletenotice(@PathVariable("id") Long id){
        System.out.println("要删除的id是"+id);
        noticeComplainService.moveSysNotice(id);
        return "redirect:/notice";
    }
    @PostMapping("/notice/update")
    public String updatebyid(SysNotice sysNotice, Principal principal){
        sysNotice.setUpdateBy(principal.getName());
//        sysNotice.setUpdateTime(new Date());
        System.out.println(sysNotice);
        noticeComplainService.updateSysNoticebyid(sysNotice);   //当对象中的字段为空的时候，是不会进行更新的
        return "redirect:/notice";
    }
    @PostMapping("/notice/add")  //通过ajax请求，更新投诉信息
    public String addconsumer(SysNotice sysNotice,Principal principal){
        sysNotice.setUpdateBy(principal.getName());
        sysNotice.setCreateBy(principal.getName());
        noticeComplainService.addSysNotice(sysNotice);
        return "redirect:/notice";
    }
}
