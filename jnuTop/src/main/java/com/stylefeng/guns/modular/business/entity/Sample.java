package com.stylefeng.guns.modular.business.entity;


import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

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

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("accept_date")
	private Date acceptDate;

	@TableField("sample_type")
	private String sampleType;
	@TableField("transport_condition")
	private String transportCondition;
	@TableField("blood_temperature")
	private Float bloodTemperature;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Sample sample = (Sample) o;

		if (sampleId != null ? !sampleId.equals(sample.sampleId) : sample.sampleId != null) return false;
		if (detectionItem != null ? !detectionItem.equals(sample.detectionItem) : sample.detectionItem != null)
			return false;
		if (labCode != null ? !labCode.equals(sample.labCode) : sample.labCode != null) return false;
		if (sampleNumber != null ? !sampleNumber.equals(sample.sampleNumber) : sample.sampleNumber != null)
			return false;
		if (subjectName != null ? !subjectName.equals(sample.subjectName) : sample.subjectName != null) return false;
		if (salesman != null ? !salesman.equals(sample.salesman) : sample.salesman != null) return false;
		if (acceptDate != null ? !acceptDate.equals(sample.acceptDate) : sample.acceptDate != null) return false;
		if (sampleType != null ? !sampleType.equals(sample.sampleType) : sample.sampleType != null) return false;
		if (transportCondition != null ? !transportCondition.equals(sample.transportCondition) : sample.transportCondition != null)
			return false;
		if (bloodTemperature != null ? !bloodTemperature.equals(sample.bloodTemperature) : sample.bloodTemperature != null)
			return false;
		if (expectedReportTime != null ? !expectedReportTime.equals(sample.expectedReportTime) : sample.expectedReportTime != null)
			return false;
		if (remark != null ? !remark.equals(sample.remark) : sample.remark != null) return false;
		if (sampleSource != null ? !sampleSource.equals(sample.sampleSource) : sample.sampleSource != null)
			return false;
		if (extracted != null ? !extracted.equals(sample.extracted) : sample.extracted != null) return false;
		if (sampleStorage != null ? !sampleStorage.equals(sample.sampleStorage) : sample.sampleStorage != null)
			return false;
		if (detectionDuration != null ? !detectionDuration.equals(sample.detectionDuration) : sample.detectionDuration != null)
			return false;
		if (picture != null ? !picture.equals(sample.picture) : sample.picture != null) return false;
		if (loginName != null ? !loginName.equals(sample.loginName) : sample.loginName != null) return false;
		if (sampleStatus != null ? !sampleStatus.equals(sample.sampleStatus) : sample.sampleStatus != null)
			return false;
		if (statusRemark != null ? !statusRemark.equals(sample.statusRemark) : sample.statusRemark != null)
			return false;
		if (subjectAge != null ? !subjectAge.equals(sample.subjectAge) : sample.subjectAge != null) return false;
		if (subjectSex != null ? !subjectSex.equals(sample.subjectSex) : sample.subjectSex != null) return false;
		if (detectionTumors != null ? !detectionTumors.equals(sample.detectionTumors) : sample.detectionTumors != null)
			return false;
		if (extractedControlBlood != null ? !extractedControlBlood.equals(sample.extractedControlBlood) : sample.extractedControlBlood != null)
			return false;
		if (bloodCollectionDate != null ? !bloodCollectionDate.equals(sample.bloodCollectionDate) : sample.bloodCollectionDate != null)
			return false;
		return plasmaSeparation != null ? plasmaSeparation.equals(sample.plasmaSeparation) : sample.plasmaSeparation == null;
	}

	@Override
	public int hashCode() {
		int result = sampleId != null ? sampleId.hashCode() : 0;
		result = 31 * result + (detectionItem != null ? detectionItem.hashCode() : 0);
		result = 31 * result + (labCode != null ? labCode.hashCode() : 0);
		result = 31 * result + (sampleNumber != null ? sampleNumber.hashCode() : 0);
		result = 31 * result + (subjectName != null ? subjectName.hashCode() : 0);
		result = 31 * result + (salesman != null ? salesman.hashCode() : 0);
		result = 31 * result + (acceptDate != null ? acceptDate.hashCode() : 0);
		result = 31 * result + (sampleType != null ? sampleType.hashCode() : 0);
		result = 31 * result + (transportCondition != null ? transportCondition.hashCode() : 0);
		result = 31 * result + (bloodTemperature != null ? bloodTemperature.hashCode() : 0);
		result = 31 * result + (expectedReportTime != null ? expectedReportTime.hashCode() : 0);
		result = 31 * result + (remark != null ? remark.hashCode() : 0);
		result = 31 * result + (sampleSource != null ? sampleSource.hashCode() : 0);
		result = 31 * result + (extracted != null ? extracted.hashCode() : 0);
		result = 31 * result + (sampleStorage != null ? sampleStorage.hashCode() : 0);
		result = 31 * result + (detectionDuration != null ? detectionDuration.hashCode() : 0);
		result = 31 * result + (picture != null ? picture.hashCode() : 0);
		result = 31 * result + (loginName != null ? loginName.hashCode() : 0);
		result = 31 * result + (sampleStatus != null ? sampleStatus.hashCode() : 0);
		result = 31 * result + (statusRemark != null ? statusRemark.hashCode() : 0);
		result = 31 * result + (subjectAge != null ? subjectAge.hashCode() : 0);
		result = 31 * result + (subjectSex != null ? subjectSex.hashCode() : 0);
		result = 31 * result + (detectionTumors != null ? detectionTumors.hashCode() : 0);
		result = 31 * result + (extractedControlBlood != null ? extractedControlBlood.hashCode() : 0);
		result = 31 * result + (bloodCollectionDate != null ? bloodCollectionDate.hashCode() : 0);
		result = 31 * result + (plasmaSeparation != null ? plasmaSeparation.hashCode() : 0);
		return result;
	}
}
