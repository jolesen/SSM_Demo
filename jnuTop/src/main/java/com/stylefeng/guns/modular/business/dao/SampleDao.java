package com.stylefeng.guns.modular.business.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import com.stylefeng.guns.modular.business.entity.Sample;

/**
 * 样本Dao
 *
 * @author fengshuonan
 * @Date 2017-08-09 14:40:25
 */
public interface SampleDao extends BaseMapper<Sample>{

    
    /**
     * 通过id删除样本信息
     * @author 罗健金
     * @date 2017年8月17日
     * @param id
     * @return 
     */
    int deleteById_self( @Param("id") Integer id);
    /**
     * 通过条件查询样本信息
     * @author 罗健金
     * @date 2017年8月17日
     * @param condition
     * @return
     */
    List<Map<String, Object>> selectSamplesByCondition( @Param("condition") String condition);
    /**
     * 通过条件查询样本信息
     * @author djb
     * @date 2017年8月23日
     * @return
     */
    List<Sample> selectByCondition(@Param("detection_item")String detection_item,@Param("sample_number")String sample_number);

}
