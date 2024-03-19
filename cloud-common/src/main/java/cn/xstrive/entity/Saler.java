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
 * 商家信息表
 * </p>
 *
 * @author xqj
 * @since 2021-02-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Saler implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "sid", type = IdType.AUTO)
    private Long sid;

    private String username;

    private String salerid;
    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 性别
     */
    private String sex;

    /**
     * 发货地址
     */
    private String address;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date ccreateTime;

    /**
     * 头像路径
     */
    private String avatar;

    /**
     * 注销标识字段（0-正常，1-注销）
     */
    private Integer isDelete;

    /**
     * 锁定标识字段（0-未锁定，1-锁定）
     */
    private Integer locked;

    /**
     * 认证信息
     */
    private String certInfo;

    private String locationplace;

    private int fans;

    private double rak;
    private double desp1;
    private double desp2;
    private double desp3;

    private Long visternum;
    private Long deposit;


}
