package cn.xstrive.wxinfo.controller;

import cn.xstrive.entity.FlatComments;
import cn.xstrive.wxinfo.service.FlatCommentsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    FlatCommentsService flatCommentsService;
    @GetMapping("/getcomments/{id}")
    public List<FlatComments> getCommentsbyid(@PathVariable("id") String id){
        List<FlatComments> list=flatCommentsService.list(new QueryWrapper<FlatComments>().eq("flat_id",id));
        return list;
    }
}
