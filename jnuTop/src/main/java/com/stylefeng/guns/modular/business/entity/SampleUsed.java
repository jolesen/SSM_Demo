package com.stylefeng.guns.modular.business.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 
 * @author 罗健金
 * @date 2017年8月26日
 *
 */
@TableName("t_sample_used")
public class SampleUsed extends Model<SampleUsed>{

    protected Serializable pkVal() {
        return this.id;
    }
    /**
     * 逻辑主键
     */
    @TableId(value="id", type= IdType.AUTO)
    private int id;
    
    /**
     * 样本id
     */
    @NotNull
    @TableField("sample_id")
    private int sampleId;
    
    /**
     * 使用者id
     */
    @NotNull
    @TableField("user_id")
    private int userId;
    
    /**
     * 样本领用日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("use_date")
    private Date useDate;
    
    /**
     * 样本归还日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("return_date")
    private Date returnDate;
    
    /**
     * 样本用量
     */
    @TableField("usage_amount")
    private double usageAmount;
    
    /**
     * 备注
     */
    @TableField("remarks")
    private String remarks;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getSampleId() {
        return sampleId;
    }
    public void setSampleId(int sampleId) {
        this.sampleId = sampleId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public Date getUseDate() {
        return useDate;
    }
    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }
    public Date getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
    public double getUsageAmount() {
        return usageAmount;
    }
    public void setUsageAmount(double usageAmount) {
        this.usageAmount = usageAmount;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    
    
    
    

}
