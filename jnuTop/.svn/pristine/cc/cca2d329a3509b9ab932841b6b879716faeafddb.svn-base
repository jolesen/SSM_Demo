package com.stylefeng.guns.modular.business.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.modular.business.entity.Sample;
import com.stylefeng.guns.modular.business.entity.SampleUsed;

/**
 * 样本领用Dao
 *
 * @author
 * @Date 2017-08-24 10:05:50
 */
public interface SampleUsedDao extends BaseMapper<SampleUsed> {

    List<Map<String, Object>> selectSampleUsedByCondition( @Param("condition") String condition);


}
