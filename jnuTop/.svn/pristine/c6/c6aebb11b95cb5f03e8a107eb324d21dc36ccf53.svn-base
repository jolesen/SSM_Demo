package com.stylefeng.guns.common.persistence.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 业务记录表，用于记录某个用户在某时间做了什么。
 * </p>
 *
 * @author wuliepeng
 * @since 2017-08-22
 */
@TableName("biz_record")
public class BizRecord extends Model<BizRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="record_id", type= IdType.AUTO)
	private Integer recordId;
    /**
     * 用户账号
     */
	@TableField("user_account")
	private String userAccount;
    /**
     * 记录创建日期
     */
	private Date createtime;
    /**
     * 业务类型，如sample
     */
	@TableField("biz_type")
	private String bizType;
    /**
     * 业务ID
     */
	@TableField("biz_id")
	private String bizId;
    /**
     * 详细信息
     */
	private String message;


	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	protected Serializable pkVal() {
		return this.recordId;
	}

	@Override
	public String toString() {
		return "BizRecord{" +
			"recordId=" + recordId +
			", userAccount=" + userAccount +
			", createtime=" + createtime +
			", bizType=" + bizType +
			", bizId=" + bizId +
			", message=" + message +
			"}";
	}
}
