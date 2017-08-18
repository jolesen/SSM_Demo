package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 部门和菜单关联表
 * </p>
 * 
 * @author zmj
 * @since 2017-8-18
 *
 */
public class DeptMenuRelation extends Model<DeptMenuRelation>{

	
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value="id",type=IdType.AUTO)
	private Integer id;
	/**
	 * 菜单id
	 */
	@TableField("menu_id")
	private Integer menuid;
	/**
	 * 部门id
	 */
	@TableField("dept_id")
	private Integer deptid;
	
	
	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "dmRelation{"+"id="+id+",menuid="+menuid+",deptid="+deptid+"}";
	}
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getMenuid() {
		return menuid;
	}


	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}


	public Integer getDeptid() {
		return deptid;
	}


	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
