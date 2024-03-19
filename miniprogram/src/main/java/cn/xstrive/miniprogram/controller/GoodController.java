package cn.xstrive.miniprogram.controller;

import cn.xstrive.entity.Goods;
import cn.xstrive.miniprogram.common.XstriveJSONResult;
import cn.xstrive.miniprogram.pojo.Good;
import cn.xstrive.miniprogram.pojo.GoodEntity;
import cn.xstrive.miniprogram.service.openfeign.WxInfoProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GoodController {

    @Autowired
    WxInfoProviderService wxInfoProviderService;
    @GetMapping("/getindex")
    public XstriveJSONResult getindex(){
        ArrayList<GoodEntity> goodEntities = new ArrayList<>();
        GoodEntity goodEntity1 = new GoodEntity();
        goodEntity1.setName("科技娱乐");
        goodEntity1.setBanner("https://pic.xstrive.cn/miniprogram_basicpic/%7DNKONJXIW%24IIZJ%7D%2951_%5B02I.png");
        ArrayList<Good> goods1 = new ArrayList<>();

        GoodEntity goodEntity2 = new GoodEntity();
        goodEntity2.setName("家电家居");
        goodEntity2.setBanner("https://pic.xstrive.cn/miniprogram_basicpic/Y%40~VK59%5DYXQ%29FR%28%5B52%40_T%606.png");
        ArrayList<Good> goods2 = new ArrayList<>();

        GoodEntity goodEntity3 = new GoodEntity();
        goodEntity3.setName("成长教育");
        goodEntity3.setBanner("https://pic.xstrive.cn/miniprogram_basicpic/%E6%88%90%E9%95%BF%E6%95%99%E8%82%B2.png");
        ArrayList<Good> goods3 = new ArrayList<>();
        List<Goods> getindex = wxInfoProviderService.getindex();
        for (Goods goods : getindex) {
            Good sp = new Good();
            sp.setGoodid(goods.getGoodId());
            sp.setGoodname(goods.getGoodName());
            sp.setList_pic_url(goods.getGoodMainpicurl());
            sp.setMin_retail_price1(goods.getGoodPrice1());
            sp.setMin_retail_price2(goods.getGoodPrice2());
            if("科技娱乐".equals(goods.getGoodCategory())){
                goods1.add(sp);
            }
            else if("家电家居".equals(goods.getGoodCategory())) {
                goods2.add(sp);
            }else{
                goods3.add(sp);
            }
        }
        goodEntity1.setGoodsList(goods1);
        goodEntity2.setGoodsList(goods2);
        goodEntity3.setGoodsList(goods3);
        goodEntities.add(goodEntity1);
        goodEntities.add(goodEntity2);
        goodEntities.add(goodEntity3);
        return XstriveJSONResult.ok(goodEntities);
    }
}
