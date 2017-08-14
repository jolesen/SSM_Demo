package com.stylefeng.guns.modular.business.entity;


import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhuangziwei 
 * @since 2017-08-10
 */
@TableName("t_sample")
public class Sample extends Model<Sample> {

    private static final long serialVersionUID = 1L;

	@TableId(value="sample_id", type= IdType.AUTO)
	private Long sampleId;
	@TableField("detection_item")
	private String detectionItem;
	@TableField("lab_code")
	private String labCode;
	@TableField("sample_number")
	private String sampleNumber;
	@TableField("subject_name")
	private String subjectName;
	private String salesman;
	@TableField("accept_date")
	private Date acceptDate;
	@TableField("sample_type")
	private String sampleType;
	@TableField("transport_condition")
	private String transportCondition;
	@TableField("blood_temperature")
	private Float bloodTemperature;
	@TableField("expected_report_time")
	private Date expectedReportTime;
	private String remark;
	@TableField("sample_source")
	private String sampleSource;
	private String extracted;
	@TableField("sample_storage")
	private String sampleStorage;
	@TableField("detection_duration")
	private Integer detectionDuration;
	private String picture;
	@TableField("login_name")
	private String loginName;
	@TableField("sample_status")
	private String sampleStatus;
	@TableField("status_remark")
	private String statusRemark;
	@TableField("subject_age")
	private Integer subjectAge;
	@TableField("subject_sex")
	private Integer subjectSex;
	@TableField("detection_tumors")
	private String detectionTumors;
	@TableField("extracted_control_blood")
	private String extractedControlBlood;
	@TableField("blood_collection_date")
	private Date bloodCollectionDate;
	@TableField("plasma_separation")
	private String plasmaSeparation;


	public Long getSampleId() {
		return sampleId;
	}

	public void setSampleId(Long sampleId) {
		this.sampleId = sampleId;
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

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public Date getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}

	public String getSampleType() {
		return sampleType;
	}

	public void setSampleType(String sampleType) {
		this.sampleType = sampleType;
	}

	public String getTransportCondition() {
		return transportCondition;
	}

	public void setTransportCondition(String transportCondition) {
		this.transportCondition = transportCondition;
	}

	public Float getBloodTemperature() {
		return bloodTemperature;
	}

	public void setBloodTemperature(Float bloodTemperature) {
		this.bloodTemperature = bloodTemperature;
	}

	public Date getExpectedReportTime() {
		return expectedReportTime;
	}

	public void setExpectedReportTime(Date expectedReportTime) {
		this.expectedReportTime = expectedReportTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSampleSource() {
		return sampleSource;
	}

	public void setSampleSource(String sampleSource) {
		this.sampleSource = sampleSource;
	}

	public String getExtracted() {
		return extracted;
	}

	public void setExtracted(String extracted) {
		this.extracted = extracted;
	}

	public String getSampleStorage() {
		return sampleStorage;
	}

	public void setSampleStorage(String sampleStorage) {
		this.sampleStorage = sampleStorage;
	}

	public Integer getDetectionDuration() {
		return detectionDuration;
	}

	public void setDetectionDuration(Integer detectionDuration) {
		this.detectionDuration = detectionDuration;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getSampleStatus() {
		return sampleStatus;
	}

	public void setSampleStatus(String sampleStatus) {
		this.sampleStatus = sampleStatus;
	}

	public String getStatusRemark() {
		return statusRemark;
	}

	public void setStatusRemark(String statusRemark) {
		this.statusRemark = statusRemark;
	}

	public Integer getSubjectAge() {
		return subjectAge;
	}

	public void setSubjectAge(Integer subjectAge) {
		this.subjectAge = subjectAge;
	}

	public Integer getSubjectSex() {
		return subjectSex;
	}

	public void setSubjectSex(Integer subjectSex) {
		this.subjectSex = subjectSex;
	}

	public String getDetectionTumors() {
		return detectionTumors;
	}

	public void setDetectionTumors(String detectionTumors) {
		this.detectionTumors = detectionTumors;
	}

	public String getExtractedControlBlood() {
		return extractedControlBlood;
	}

	public void setExtractedControlBlood(String extractedControlBlood) {
		this.extractedControlBlood = extractedControlBlood;
	}

	public Date getBloodCollectionDate() {
		return bloodCollectionDate;
	}

	public void setBloodCollectionDate(Date bloodCollectionDate) {
		this.bloodCollectionDate = bloodCollectionDate;
	}

	public String getPlasmaSeparation() {
		return plasmaSeparation;
	}

	public void setPlasmaSeparation(String plasmaSeparation) {
		this.plasmaSeparation = plasmaSeparation;
	}

	@Override
	protected Serializable pkVal() {
		return this.sampleId;
	}

	@Override
	public String toString() {
		return "TSample{" +
			"sampleId=" + sampleId +
			", detectionItem=" + detectionItem +
			", labCode=" + labCode +
			", sampleNumber=" + sampleNumber +
			", subjectName=" + subjectName +
			", salesman=" + salesman +
			", acceptDate=" + acceptDate +
			", sampleType=" + sampleType +
			", transportCondition=" + transportCondition +
			", bloodTemperature=" + bloodTemperature +
			", expectedReportTime=" + expectedReportTime +
			", remark=" + remark +
			", sampleSource=" + sampleSource +
			", extracted=" + extracted +
			", sampleStorage=" + sampleStorage +
			", detectionDuration=" + detectionDuration +
			", picture=" + picture +
			", loginName=" + loginName +
			", sampleStatus=" + sampleStatus +
			", statusRemark=" + statusRemark +
			", subjectAge=" + subjectAge +
			", subjectSex=" + subjectSex +
			", detectionTumors=" + detectionTumors +
			", extractedControlBlood=" + extractedControlBlood +
			", bloodCollectionDate=" + bloodCollectionDate +
			", plasmaSeparation=" + plasmaSeparation +
			"}";
	}
}
