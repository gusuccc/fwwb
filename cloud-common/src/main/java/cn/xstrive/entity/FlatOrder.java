package cn.xstrive.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xqj
 * @since 2021-03-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FlatOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId(value = "foid", type = IdType.AUTO)
    private Long foid;

    /**
     * 订单id
     */
    private String orderid;


    private String flatid;
    private String flatname;
    /**
     * 开始日期
     */
    private Date begindata;

    /**
     * 结束日期
     */
    private Date enddata;

    /**
     * 房主id
     */
    private String belongedHostid;

    /**
     * 租户id
     */
    private String hirepersonid;

    /**
     * 租户联系方式
     */
    private String contactwayHire;

    /**
     * 押金
     */
    private Long deposit;

    /**
     * 单价
     */
    @TableField("UnitPrice")
    private Long UnitPrice;

    /**
     * 天数
     */
    private Integer daynum;

    /**
     * 抵扣金额
     */
    @TableField("DeductionMoney")
    private Integer DeductionMoney;

    /**
     * 创建时间
     */
    private String orderstatus;

    private String spurl;
    @TableField(fill = FieldFill.INSERT)
    private Date focreateTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date foupdateTime;


}
