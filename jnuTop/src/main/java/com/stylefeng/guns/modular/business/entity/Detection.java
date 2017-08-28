package com.stylefeng.guns.modular.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 
 * @author ZhuangJieXian
 * 样品检测结果
 *
 */
@TableName("t_detection")
public class Detection extends Model<Project>{
	
	@TableId(value="id",type=IdType.AUTO)
	private Integer id;
	
	//样本id
	@TableField("sample_id")
	private String sampleId;
	
	
	//检测结果
	@TableField("result")
	private String result;
	
	//检查者id
	@TableField("detector_id")
	private String detectorId;
	
	//突变点
	@TableField("mutation_site")
	private String mutationSite;
	
	//风险
	@TableField("risk")
	private String risk;
	
	//检测时间
	@TableField("detection_date")
	private Date detectionDate;
	
	//备注
	@TableField("remarks")
	private String remarks;
	
	
	
	
	
	
	public Integer getId() {
		return id;
	}






	public void setId(Integer id) {
		this.id = id;
	}






	public String getSampleId() {
		return sampleId;
	}






	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}






	public String getResult() {
		return result;
	}






	public void setResult(String result) {
		this.result = result;
	}






	






	public String getDetectorId() {
		return detectorId;
	}






	public void setDetectorId(String detectorId) {
		this.detectorId = detectorId;
	}






	public String getMutationSite() {
		return mutationSite;
	}






	public void setMutationSite(String mutationSite) {
		this.mutationSite = mutationSite;
	}






	public String getRisk() {
		return risk;
	}






	public void setRisk(String risk) {
		this.risk = risk;
	}






	public Date getDetectionDate() {
		return detectionDate;
	}






	public void setDetectionDate(Date detectionDate) {
		this.detectionDate = detectionDate;
	}






	public String getRemarks() {
		return remarks;
	}






	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}






	@Override
	protected Serializable pkVal() {
		 return this.id;
	}






	@Override
	public String toString() {
		return "Detection [id=" + id + ", sampleId=" + sampleId + ", result="
				+ result + ", detectorId=" + detectorId + ", mutationSite="
				+ mutationSite + ", risk=" + risk + ", detectionDate="
				+ detectionDate + ", remarks=" + remarks + "]";
	}











	
	
	
	

}
