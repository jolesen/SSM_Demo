package com.stylefeng.guns.modular.business.entity;


import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng
 * @since 2017-08-17
 */
@TableName("t_excelmodel")
public class ExcelModel extends Model<ExcelModel> {

    private static final long serialVersionUID = 1343253456L;

    @TableId("model_name")
   	private String modelName;
   	@TableField("model_url")
   	private String modelUrl;


   	public String getModelName() {
   		return modelName;
   	}

   	public void setModelName(String modelName) {
   		this.modelName = modelName;
   	}

   	public String getModelUrl() {
   		return modelUrl;
   	}

   	public void setModelUrl(String modelUrl) {
   		this.modelUrl = modelUrl;
   	}

   	@Override
   	protected Serializable pkVal() {
   		return this.modelName;
   	}

   	@Override
   	public String toString() {
   		return "TExcelmodel{" +
   			"modelName=" + modelName +
   			", modelUrl=" + modelUrl +
   			"}";
   	}
}
