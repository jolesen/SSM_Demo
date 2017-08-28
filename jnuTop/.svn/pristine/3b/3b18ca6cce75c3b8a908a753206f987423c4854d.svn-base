package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 通知-用户关系表
 * @author zmj
 * @since 2017年8月23日上午10:21:57
 *
 */

@TableName("notice_user_relation")
public class NoticeUserRelation extends Model<NoticeUserRelation>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@TableId(value="id",type=IdType.AUTO)
	int id;
	
	/**
	 * 通知id
	 */
	@TableField("notice_id")
	int noticeid;
	
	/**
	 * 用户id
	 */
	@TableField("user_id")
	int userid;

	/**
	 * 是否已阅
	 * 0:否
	 * 1:是
	 */
	@TableField("readed")
	int readed;
	
	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "NoticeUserRelation{"+"id="+id+",noticeid="+noticeid+",userid="+userid+"}";
	}

	public int getRead() {
		return readed;
	}

	public void setRead(int read) {
		this.readed = read;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNoticeid() {
		return noticeid;
	}

	public void setNoticeid(int noticeid) {
		this.noticeid = noticeid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
