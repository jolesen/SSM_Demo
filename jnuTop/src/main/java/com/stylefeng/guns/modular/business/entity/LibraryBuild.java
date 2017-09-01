package com.stylefeng.guns.modular.business.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 文库制备
 * @author zmj
 * @since 2017年8月28日下午3:32:28
 *
 */

@TableName("t_library_build")
public class LibraryBuild extends Model<LibraryBuild>{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@TableId(value="library_build_id")
	private Integer id;
	
	//样本Id
	@TableField(value="sample_id")
	private Integer sampleId;
	
	//任务单名称
	@TableField(value = "mission_list_name")
	private String missionListName;
	
	//96孔板位置
	@TableField(value= "well_plates_96")
	private String wellPlates96;
	
	//回溶体积(ul)
	@TableField(value = "redissolution_bulk")
	private Double redissolutionBulk;
	
	//打断胶图位置
	@TableField(value= "cut_position_image")
	private String cutPostionImage;
	
	//连接接头后浓度(ng/ul)
	@TableField(value= "consistency_connection")
	private Double consistencyConnection;
	
	//连接接头后总量(ng)
	@TableField(value = "total_connection")
	private Double totalConnection;
	
	//文库号
	@TableField(value = "library_code")
	private String libraryCode;
	
	//INDEX标签7
	@TableField(value = "index7")
	private String index7;
	
	//INDEX标签5
	@TableField(value = "index5")
	private String index5;
	
	//文库体积ul
	@TableField(value = "library_bulk")
	private Double libraryBulk;
	
	//pre-PCR循环数
	@TableField(value = "loop_pcr_times")
	private Integer loopPcrTimes;
	
	//建库开始时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField(value = "build_start_date")
	private Date buildStartDate;
	
	//建库结束时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField(value = "build_end_date")
	private Date buildEndDate;
	
	//QB浓度
	@TableField(value = "qb")
	private Double qb;
	
	//备注
	@TableField(value = "remarks")
	private String remarks;
	
	//操作人
	@TableField(value = "operater")
	private String operater;
	
	//项目编码
	@TableField(value = "project_code")
	private String projectCode;
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSampleId() {
		return sampleId;
	}

	public void setSampleId(Integer sampleId) {
		this.sampleId = sampleId;
	}

	public String getMissionListName() {
		return missionListName;
	}

	public void setMissionListName(String missionListName) {
		this.missionListName = missionListName;
	}

	public String getWellPlates96() {
		return wellPlates96;
	}

	public void setWellPlates96(String wellPlates96) {
		this.wellPlates96 = wellPlates96;
	}

	public Double getRedissolutionBulk() {
		return redissolutionBulk;
	}

	public void setRedissolutionBulk(Double redissolutionBulk) {
		this.redissolutionBulk = redissolutionBulk;
	}

	public String getCutPostionImage() {
		return cutPostionImage;
	}

	public void setCutPostionImage(String cutPostionImage) {
		this.cutPostionImage = cutPostionImage;
	}

	public Double getConsistencyConnection() {
		return consistencyConnection;
	}

	public void setConsistencyConnection(Double consistencyConnection) {
		this.consistencyConnection = consistencyConnection;
	}

	public Double getTotalConnection() {
		return totalConnection;
	}

	public void setTotalConnection(Double totalConnection) {
		this.totalConnection = totalConnection;
	}

	public String getLibraryCode() {
		return libraryCode;
	}

	public void setLibraryCode(String libraryCode) {
		this.libraryCode = libraryCode;
	}

	public String getIndex7() {
		return index7;
	}

	public void setIndex7(String index7) {
		this.index7 = index7;
	}

	public String getIndex5() {
		return index5;
	}

	public void setIndex5(String index5) {
		this.index5 = index5;
	}

	public Double getLibraryBulk() {
		return libraryBulk;
	}

	public void setLibraryBulk(Double libraryBulk) {
		this.libraryBulk = libraryBulk;
	}

	public Integer getLoopPcrTimes() {
		return loopPcrTimes;
	}

	public void setLoopPcrTimes(Integer loopPcrTimes) {
		this.loopPcrTimes = loopPcrTimes;
	}

	public Date getBuildStartDate() {
		return buildStartDate;
	}

	public void setBuildStartDate(Date buildStartDate) {
		this.buildStartDate = buildStartDate;
	}

	public Date getBuildEndDate() {
		return buildEndDate;
	}

	public void setBuildEndDate(Date buildEndDate) {
		this.buildEndDate = buildEndDate;
	}

	public Double getQb() {
		return qb;
	}

	public void setQb(Double qb) {
		this.qb = qb;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOperater() {
		return operater;
	}

	public void setOperater(String operater) {
		this.operater = operater;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	protected Serializable pkVal() {
		return id;
	}

	@Override
	public String toString() {
		return "LibraryBuild{"+
				"id="+id
				+",SampleId="+sampleId
				+",MissionListName="+missionListName
				+",WellPlates96="+wellPlates96
				+",RedissolutionBulk="+redissolutionBulk
				+",CutPostionImage="+cutPostionImage
				+",ConsistencyConnection="+consistencyConnection
				+",TotalConnection="+totalConnection
				+",LibraryCode="+libraryCode
				+",Index7="+index7
				+",Index5="+index5
				+"，LibraryBulk="+libraryBulk
				+",LoopPcrTimes="+loopPcrTimes
				+",BuildStartdate="+buildStartDate
				+",BuildEndDate="+buildEndDate;
	}

	
}
