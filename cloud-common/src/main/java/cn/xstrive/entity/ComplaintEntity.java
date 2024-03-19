package cn.xstrive.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 投诉信息表
 * </p>
 *
 * @author xqj
 * @since 2021-02-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("complainttable")
public class ComplaintEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 投诉ID
     */
    @TableId(value = "complaint_id", type = IdType.AUTO)
    private Long complaintId;

    /**
     * 投诉用户
     */
    private String complaintUser;

    /**
     * 被投诉商家
     */
    private String complaintedSeller;

    /**
     * 投诉原因
     */
    private String complaintReason;

    /**
     * 投诉时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date complaintTime;

    /**
     * 投诉进度
     */
    private String complaintProcess;

    /**
     * 最后处理时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date lastupdateTime;

    /**
     * 投诉结果
     */
    private String complaintResult;


}
