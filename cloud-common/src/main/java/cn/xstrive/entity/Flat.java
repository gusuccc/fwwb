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
 * 房屋信息表
 * </p>
 *
 * @author xqj
 * @since 2021-03-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Flat implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 房屋id
     */
    private String flatId;

    /**
     * 房屋名称
     */
    private String flatName;

    /**
     * 房屋描述
     */
    private String flatDesc;

    /**
     * 房屋地址-省
     */
    private String flatAddrSheng;

    /**
     * 房屋地址-市
     */
    private String flatAddrShi;

    /**
     * 房屋地址-县
     */
    private String flatAddrXian;

    /**
     * 详细地址
     */
    private String flatAddrDetail;

    /**
     * 预定须知（由房东指制定） 如入住时间、不带宠物等
     */
    private String flatRequest;

    /**
     * 房屋面积
     */
    private Integer flatArea;

    /**
     * 房屋价格（天）
     */
    private Integer flatPrice;

    /**
     * 主图片url
     */
    private String flatMainpicurl;

    /**
     * 房屋评分0-5star
     */
    private Integer flatScore;

    /**
     * 房屋状态
     */
    private Integer flatCondition;

    /**
     * 户主id
     */
    private String saleId;

    /**
     * 房屋年租天数
     */
    private Integer flagSalenum;

    /**
     * 房屋分类
     */
    private String flagCategory;

    /**
     * 房屋特色
     */
    private String flatFeature;

    private Long deposit;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date fcreateTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date fupdateTime;


}
