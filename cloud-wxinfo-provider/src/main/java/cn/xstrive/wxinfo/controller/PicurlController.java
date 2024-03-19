package cn.xstrive.wxinfo.controller;

import cn.xstrive.entity.PicUrl;
import cn.xstrive.wxinfo.service.PicUrlService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PicurlController {
    @Autowired
    PicUrlService picUrlService;
    @GetMapping("/geturls/{id}")
    public List<PicUrl> geturlsById(@PathVariable("id") String id){
        List<PicUrl> list=picUrlService.list(new QueryWrapper<PicUrl>().eq("sale_id",id));
        return list;
    }
}
