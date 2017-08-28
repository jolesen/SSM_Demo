package com.stylefeng.guns.modular.business.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 
 * @author 罗健金
 * @date 2017年8月21日
 *
 */
@TableName("t_project")
public class Project extends Model<Project> {
    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**
     * 项目名称
     */
    @NotNull
    @NotBlank
    @TableField("name")
    private String name;
    /**
     * 项目排序
     */
    @NotNull
    @NotBlank
    @TableField("project_order")
    private Integer projectOrder;
    /**
     * 是否是工作日  默认是1 是工作日
     */
    @TableField("is_workingday")
    private String isWorkingday;
    /**
     * 项目持续时间
     */
    @TableField("duration_time")
    private Integer durationTime;
    /**
     * 项目否启用  默认是1  启用
     */
    @TableField("is_used")
    private Integer isUsed;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getProjectOrder() {
        return projectOrder;
    }

    public void setProjectOrder(Integer projectOrder) {
        this.projectOrder = projectOrder;
    }



    public String getIsWorkingday() {
        return isWorkingday;
    }

    public void setIsWorkingday(String isWorkingday) {
        this.isWorkingday = isWorkingday;
    }

    public Integer getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(Integer durationTime) {
        this.durationTime = durationTime;
    }

    public Integer getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Project [id=" + id + ", name=" + name + ", projectOrder=" + projectOrder + ", isWorkingday="
                + isWorkingday + ", durationTime=" + durationTime + ", isUsed=" + isUsed + "]";
    }

  

}
