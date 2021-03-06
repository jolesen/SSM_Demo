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
    /**
     * 查找可领用的样本的信息
     * @author 罗健金
     * @date 2017年8月30日
     * @param condition
     * @return
     */
    List<Map<String, Object>> listSampleUsedByCondition( @Param("condition") String condition);
    /**
     * 查看样本领用的记录列表
     * @author 罗健金
     * @date 2017年8月30日
     * @return
     */
    List<Map<String,Object>> listHadSampleUsed();
    /**
     * 查找特定id的样本领用记录
     * @author 罗健金
     * @date 2017年8月31日
     * @param id
     * @return
     */
    List<Map<String, Object>> listHadSampleUsedById(Integer sampleId);
   
    


}
