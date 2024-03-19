package cn.xstrive.wxinfo.controller;

import cn.xstrive.entity.FlatOrder;
import cn.xstrive.wxinfo.service.FlatOrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    FlatOrderService flatOrderService;

    @PostMapping("/getorderinfo")
    public List<FlatOrder> getorderinfo(@RequestBody String openid){
        QueryWrapper<FlatOrder> hirepersonid = new QueryWrapper<FlatOrder>().eq("hirepersonid", openid);
        return  flatOrderService.list(hirepersonid);
    }

    @PostMapping("/addorderinfo")
    public void addorderinfo(@RequestBody FlatOrder flatOrder){
        flatOrderService.save(flatOrder);
    }
    @PostMapping("/updateorder")
    public void updateorder(@RequestBody FlatOrder flatOrder){
        String orderid = flatOrder.getOrderid();
        UpdateWrapper<FlatOrder> wrapper = new UpdateWrapper<FlatOrder>().eq("orderid", orderid);
        flatOrderService.update(flatOrder,wrapper);
    }
}
