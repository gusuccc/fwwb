package cn.xstrive.miniprogram.pojo;

import cn.xstrive.entity.Shopcar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarSaler {
    private String shopname;
    private boolean selected;
    private List<Shopcar> goodsInfo;
}
