package cn.xstrive.wxinfo.controller;


import cn.xstrive.entity.FlatComments;
import cn.xstrive.entity.FlatOrder;
import cn.xstrive.wxinfo.service.FlatCommentsService;
import cn.xstrive.wxinfo.service.FlatOrderService;
import cn.xstrive.wxinfo.service.FlatService;
import cn.xstrive.wxinfo.service.PicUrlService;
import cn.xstrive.wxinfo.util.UniqueId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xqj
 * @since 2021-03-26
 */
@RestController
public class FlatOrderController {

    @Autowired
    FlatOrderService flatOrderService;
    @Autowired
    FlatCommentsService flatCommentsService;
    @Autowired
    PicUrlService picUrlService;
    
    @GetMapping("/ordertest")
    public FlatOrder ordertest(){
        FlatOrder flatOrder = new FlatOrder();
        flatOrder.setBegindata(new Date());
        flatOrder.setOrderid(UniqueId.getuniqueid());
        flatOrder.setEnddata(new Date());
        flatOrder.setFlatid("testid");
        flatOrder.setBelongedHostid("testid");
        flatOrder.setHirepersonid("testid");
        flatOrder.setContactwayHire("xxxxxxx");
        flatOrder.setDeposit((long)100);
        flatOrder.setUnitPrice((long)100);
        flatOrder.setDaynum(3);
        flatOrder.setDeductionMoney(100);
        flatOrderService.save(flatOrder);
        return flatOrder;
    }
    @GetMapping("/commenttest")
    public FlatComments commentstest(){
        FlatComments flatComments=new FlatComments();
        flatComments.setFlatId("f0001");
        flatComments.setUserid("0004");
        flatComments.setScore(3);
        flatComments.setDescription("漏水了");
        flatCommentsService.save(flatComments);
        return flatComments;
    }
}

