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
 * 房屋评论
 * </p>
 *
 * @author xqj
 * @since 2021-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FlatComments implements Serializable {

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
     * 用户id
     */
    private String userid;

    /**
     * 评分
     */
    private Integer score;

    /**
     * 评价描述
     */
    private String description;

    @TableField(fill = FieldFill.INSERT)
    private Date fccreateTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date fcupdateTime;


}
