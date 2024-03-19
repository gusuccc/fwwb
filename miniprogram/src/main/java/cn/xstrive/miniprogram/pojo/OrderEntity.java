package cn.xstrive.miniprogram.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class OrderEntity {
    private String orderid;
    private String orderstatus;
    private Date add_time;
    private double actual_price;
    private double freight_price;
    private String spid;
    private String spurl;
    private String spname;
}
