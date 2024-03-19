package cn.xstrive.admin.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author xqj
 * @since 2021-02-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 管理员id
     */
    @TableId(value = "admin_userid", type = IdType.AUTO)
    private Long adminUserid;

    /**
     * 管理员用户名
     */
    private String ausername;

    /**
     * 管理员密码
     */
    private String apassword;

    /**
     * 管理员权限
     */
    private String aroles;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date signtime;


}
