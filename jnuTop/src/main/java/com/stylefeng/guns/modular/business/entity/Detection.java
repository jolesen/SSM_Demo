package com.stylefeng.guns.modular.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

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
	
	@TableId(value="detection_id",type=IdType.AUTO)
	private Integer detectionId;
	
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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("detection_date")
	private Date detectionDate;
	
	//备注
	@TableField("remarks")
	private String remarks;
	
	//检测者
	@TableField("detector_name")
	private String detectorName;
	

	private String sampleNumber;
	
	private String labCode;
	
	
	
	

	public String getSampleNumber() {
		return sampleNumber;
	}

	public void setSampleNumber(String sampleNumber) {
		this.sampleNumber = sampleNumber;
	}

	public String getLabCode() {
		return labCode;
	}

	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}

	public Integer getDetectionId() {
		return detectionId;
	}

	public void setDetectionId(Integer detectionId) {
		this.detectionId = detectionId;
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

	public String getDetectorName() {
		return detectorName;
	}

	public void setDetectorName(String detectorName) {
		this.detectorName = detectorName;
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
		 return this.detectionId;
	}

	@Override
	public String toString() {
		return "Detection [detectionId=" + detectionId + ", sampleId="
				+ sampleId + ", result=" + result + ", detectorId="
				+ detectorId + ", mutationSite=" + mutationSite + ", risk="
				+ risk + ", detectionDate=" + detectionDate + ", remarks="
				+ remarks + ", detectorName=" + detectorName
				+ ", sampleNumber=" + sampleNumber + ", labCode=" + labCode
				+ "]";
	}

	

	

}
