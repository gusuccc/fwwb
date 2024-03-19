package cn.xstrive.miniprogram.pojo;

import lombok.Data;

import java.util.List;
@Data
public class GoodEntity {
    private String banner;
    private String name;
    private List<Good> goodsList;
}
