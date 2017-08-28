package com.stylefeng.guns.modular.business.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 样本DNA提取表
 * </p>
 *
 * @author John
 * @since 2017-08-25
 */
@TableName("t_sample_extracted_dna")
public class SampleExtractedDna extends Model<SampleExtractedDna> {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 样本ID
     */
	@TableField("sample_id")
	private Long sampleId;
    /**
     * 提取者ID
     */
	@TableField("extract_people_id")
	private Integer extractPeopleId;
    /**
     * 提取日期
     */
	@TableField("extract_date")
	private Date extractDate;
    /**
     * 存储位置
     */
	@TableField("storage_location")
	private String storageLocation;
    /**
     * 用nanodrop测得的数据
     */
	private Double nanodrop;
    /**
     * 用qubit测的数据
     */
	private Double qubit;
    /**
     * 260nm和280nm下吸光光度比值
     */
	private Double od260280;
    /**
     * 260nm和230nm下吸光光度比值
     */
	private Double od260230;
    /**
     * 图片存储位置
     */
	private String image;
    /**
     * 是否完成提取，1完成，0未完成
     */
	@TableField("is_complete")
	private Integer isComplete;
    /**
     * 备注
     */
	private String remarks;
	@TableField("is_dna")
	private Integer isDna;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getSampleId() {
		return sampleId;
	}

	public void setSampleId(Long sampleId) {
		this.sampleId = sampleId;
	}

	public Integer getExtractPeopleId() {
		return extractPeopleId;
	}

	public void setExtractPeopleId(Integer extractPeopleId) {
		this.extractPeopleId = extractPeopleId;
	}

	public Date getExtractDate() {
		return extractDate;
	}

	public void setExtractDate(Date extractDate) {
		this.extractDate = extractDate;
	}

	public String getStorageLocation() {
		return storageLocation;
	}

	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
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

	public Double getOd260280() {
		return od260280;
	}

	public void setOd260280(Double od260280) {
		this.od260280 = od260280;
	}

	public Double getOd260230() {
		return od260230;
	}

	public void setOd260230(Double od260230) {
		this.od260230 = od260230;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(Integer isComplete) {
		this.isComplete = isComplete;
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
		return "TSampleExtracted{" +
			"id=" + id +
			", sampleId=" + sampleId +
			", extractPeopleId=" + extractPeopleId +
			", extractDate=" + extractDate +
			", storageLocation=" + storageLocation +
			", nanodrop=" + nanodrop +
			", qubit=" + qubit +
			", od260280=" + od260280 +
			", od260230=" + od260230 +
			", image=" + image +
			", isComplete=" + isComplete +
			", remarks=" + remarks +
			"}";
	}
}
