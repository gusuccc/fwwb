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
 * 顾客信息表
 * </p>
 *
 * @author xqj
 * @since 2021-02-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cid", type = IdType.AUTO)
    private Long cid;

    private String username;

    /**
     * 昵称
     */
    private String nickName;
    /**
     * 性别
     */
    private String sex;
    /**
     * 收货地址
     */
    private String address;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

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

    private String openid;

    private String insider;

    private Long integral;
    private Long moneynum;

}
