package cn.xstrive.wxinfo.controller;

import cn.xstrive.entity.Goods;
import cn.xstrive.wxinfo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GoodController {

    @Autowired
    GoodsService goodsService;

    @GetMapping("/getindex")
    public List<Goods> getindex(){
        return goodsService.list(null);
    }

}
