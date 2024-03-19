package cn.xstrive.wxinfo.controller;


import cn.xstrive.entity.Flat;
import cn.xstrive.wxinfo.mapper.FlatMapper;
import cn.xstrive.wxinfo.service.FlatService;
import cn.xstrive.wxinfo.service.impl.FlatServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 房屋信息表 前端控制器
 * </p>
 *
 * @author xqj
 * @since 2021-03-26
 */
@RestController
public class FlatController {
    @Autowired
    FlatService flatService;
    @GetMapping("/flatdetail/{id}")
    public Flat getFlat(@PathVariable("id") String id){
        Flat flat=flatService.getOne(new QueryWrapper<Flat>().eq("flat_id",id));
        //System.out.println(flat);
        return  flat;
    }

    @GetMapping("/getflatlist")
    public List<Flat>getflatlist(){
        List<Flat> list = flatService.list(null);
        return list;
    }
    @GetMapping("/getflatbycity/{city}")
    public List<Flat> getFlatbyId(@PathVariable("city") String city){
        List<Flat> list=flatService.list(new QueryWrapper<Flat>().like("flat_addr_shi",city));
        return list;
    }
}

