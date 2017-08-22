package com.stylefeng.guns.modular.business.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("process_dna_extract")
public class ProcessDnaExtract extends Model<ProcessDnaExtract> {

    private static final long serialVersionUID = 1L;

    /**
     * dna提取的id
     */
	@TableId(value="dna_id", type= IdType.AUTO)
	private Long dnaId;
    /**
     * 检测项目
     */
	@TableField("detection_item")
	private String detectionItem;
    /**
     * 实验室编码
     */
	@TableField("lab_code")
	private String labCode;
    /**
     * 样本编号
     */
	@TableField("sample_number")
	private String sampleNumber;
    /**
     * 提取人
     */
	@TableField("extract_man")
	private String extractMan;
    /**
     * 提取时间
     */
	@TableField("extract_time")
	private Date extractTime;
    /**
     * 我也不知道是什么
     */
	@TableField("NANODROP")
	private Double nanodrop;
    /**
     * 我也不知道是什么
     */
	@TableField("QUBIT")
	private Double qubit;
    /**
     * dna储存位置
     */
	@TableField("dna_location")
	private String dnaLocation;
    /**
     * 备注
     */
	private String remark;
    /**
     * 使用人1
     */
	@TableField("using_man_1")
	private String usingMan1;
    /**
     * 使用时间1
     */
	@TableField("using_time_1")
	private Date usingTime1;
    /**
     * 归还时间1
     */
	@TableField("return_time_1")
	private Date returnTime1;
    /**
     * 使用人2
     */
	@TableField("using_man_2")
	private String usingMan2;
    /**
     * 使用时间2
     */
	@TableField("using_time_2")
	private Date usingTime2;
    /**
     * 归还时间2
     */
	@TableField("return_time_2")
	private Date returnTime2;


	public Long getDnaId() {
		return dnaId;
	}

	public void setDnaId(Long dnaId) {
		this.dnaId = dnaId;
	}

	public String getDetectionItem() {
		return detectionItem;
	}

	public void setDetectionItem(String detectionItem) {
		this.detectionItem = detectionItem;
	}

	public String getLabCode() {
		return labCode;
	}

	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}

	public String getSampleNumber() {
		return sampleNumber;
	}

	public void setSampleNumber(String sampleNumber) {
		this.sampleNumber = sampleNumber;
	}

	public String getExtractMan() {
		return extractMan;
	}

	public void setExtractMan(String extractMan) {
		this.extractMan = extractMan;
	}

	public Date getExtractTime() {
		return extractTime;
	}

	public void setExtractTime(Date extractTime) {
		this.extractTime = extractTime;
	}

	public Double getNanodrop() {
		return nanodrop;
	}

	public void setNanodrop(Double nanodrop) {
		this.nanodrop = nanodrop;
	}

	public Double getQubit() {
		return qubit;
	}

	public void setQubit(Double qubit) {
		this.qubit = qubit;
	}

	public String getDnaLocation() {
		return dnaLocation;
	}

	public void setDnaLocation(String dnaLocation) {
		this.dnaLocation = dnaLocation;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUsingMan1() {
		return usingMan1;
	}

	public void setUsingMan1(String usingMan1) {
		this.usingMan1 = usingMan1;
	}

	public Date getUsingTime1() {
		return usingTime1;
	}

	public void setUsingTime1(Date usingTime1) {
		this.usingTime1 = usingTime1;
	}

	public Date getReturnTime1() {
		return returnTime1;
	}

	public void setReturnTime1(Date returnTime1) {
		this.returnTime1 = returnTime1;
	}

	public String getUsingMan2() {
		return usingMan2;
	}

	public void setUsingMan2(String usingMan2) {
		this.usingMan2 = usingMan2;
	}

	public Date getUsingTime2() {
		return usingTime2;
	}

	public void setUsingTime2(Date usingTime2) {
		this.usingTime2 = usingTime2;
	}

	public Date getReturnTime2() {
		return returnTime2;
	}

	public void setReturnTime2(Date returnTime2) {
		this.returnTime2 = returnTime2;
	}

	@Override
	protected Serializable pkVal() {
		return this.dnaId;
	}

	@Override
	public String toString() {
		return "ProcessDnaExtract{" +
			"dnaId=" + dnaId +
			", detectionItem=" + detectionItem +
			", labCode=" + labCode +
			", sampleNumber=" + sampleNumber +
			", extractMan=" + extractMan +
			", extractTime=" + extractTime +
			", nanodrop=" + nanodrop +
			", qubit=" + qubit +
			", dnaLocation=" + dnaLocation +
			", remark=" + remark +
			", usingMan1=" + usingMan1 +
			", usingTime1=" + usingTime1 +
			", returnTime1=" + returnTime1 +
			", usingMan2=" + usingMan2 +
			", usingTime2=" + usingTime2 +
			", returnTime2=" + returnTime2 +
			"}";
	}
}
