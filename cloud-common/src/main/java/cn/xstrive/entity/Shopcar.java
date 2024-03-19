package cn.xstrive.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 心愿单表
 * </p>
 *
 * @author xqj
 * @since 2021-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Shopcar implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "carid", type = IdType.AUTO)
    private Long carid;

    /**
     * 用户openid
     */
    private String uopenid;

    /**
     * 商品数
     */
    private Integer spnum;

    private Double price;
    /**
     * 商品图片
     */
    private String spavatar;

    /**
     * 商品名
     */
    private String spname;

    /**
     * 是否选中
     */
    private Boolean isselected;

    /**
     * 商家名（店铺名）
     */
    private String salername;

    private String spid;

    private Date begintime;
    private Date endtime;

    private double deposit;


    @TableField(fill = FieldFill.INSERT)
    private Date carcreateTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date carupdateTime;


}
