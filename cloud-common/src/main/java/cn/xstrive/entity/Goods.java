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
 * 货物信息表
 * </p>
 *
 * @author xqj
 * @since 2021-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品id
     */
    private String goodId;

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 商品描述
     */
    private String goodDesc;

    /**
     * 预定须知（由商家制定）
     */
    private String goodRequest;

    /**
     * 租赁价格（天）
     */
    private Double goodPrice1;

    /**
     * 租赁价格（天）
     */
    private Double goodPrice2;

    /**
     * 主图片url
     */
    private String goodMainpicurl;

    /**
     * 商品评分0-5star
     */
    private Integer goodScore;

    /**
     * 商品状态 1可借，0不可借
     */
    private Integer goodCondition;

    /**
     * 商家id
     */
    private String saleId;

    /**
     * 商品年租天数
     */
    private Integer goodSalenum;

    /**
     * 商品分类
     */
    private String goodCategory;

    /**
     * 押金
     */
    private Long deposit;

    @TableField(fill = FieldFill.INSERT)
    private Date fcgreateTime;

    private Date fugpdateTime;


}
