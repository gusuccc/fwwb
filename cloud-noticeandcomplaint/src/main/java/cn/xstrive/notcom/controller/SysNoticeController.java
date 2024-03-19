package cn.xstrive.notcom.controller;


import cn.xstrive.entity.ComplaintEntity;
import cn.xstrive.entity.Customer;
import cn.xstrive.entity.SysNotice;
import cn.xstrive.notcom.mapper.SysNoticeMapper;
import cn.xstrive.notcom.service.ComplaintEntityService;
import cn.xstrive.notcom.service.SysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 通知公告表 前端控制器
 * </p>
 *
 * @author xqj
 * @since 2021-02-16
 */
@RestController
public class SysNoticeController {

//    @Autowired
//    SysNoticeMapper sysNoticeMapper;

    @Autowired
    SysNoticeService sysNoticeService;
    /*
    获取公告列表
     */
    @GetMapping("/sysNotice/list")
    public List<SysNotice> SysNoticelist(){
        List<SysNotice> list= sysNoticeService.list(null);
        return list;
    }
    /*
    根据客户端发出请求获取的id删除指定公告信息
     */
    @GetMapping("/sysNotice/{id}")
    public void moveSysNotice(@PathVariable("id") Long id){
        System.out.println("要删除的id是:"+id);
        sysNoticeService.removeById(id);
    }
    /*
    接收前端post请求中的请求体
    更新对应公告信息
     */
    @PostMapping("/upsysNotice")
    public void updateSysNoticebyid(@RequestBody SysNotice sysNotice){
        sysNoticeService.updateById(sysNotice);
    }
    /*
    接收前端post请求中的请求体
    增加公告信息
     */
    @PostMapping("/addsysNotice")
    public void addSysNotice(@RequestBody SysNotice sysNotice){
        sysNoticeService.save(sysNotice);
    }

}

