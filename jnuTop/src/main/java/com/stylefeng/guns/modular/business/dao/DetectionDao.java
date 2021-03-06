package com.stylefeng.guns.modular.business.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.modular.business.entity.Detection;
import com.stylefeng.guns.modular.business.entity.Sample;
import com.stylefeng.guns.modular.business.entity.SampleExtractedDna;

/**
 * 检测结果Dao
 *
 * @author
 * @Date 2017-08-23 15:52:04
 */
public interface DetectionDao extends  BaseMapper<Detection>{
	/**
	 * 通过id查询detection对象
	 * @param id
	 * @return
	 */
	Detection findDetectionById(Integer id);
	
	
	List<Map<String,Object>>  selectDetectionsByCondition(String condition);


	/**
	 * 更新检测结果
	 * @param detection
	 */
	void update(Detection detection);
	
	/**
	 * 通过sampleNum获取sampleId
	 * @param sampleNum
	 * @return
	 */
	Integer selectSampleIdBySampleNum(String sampleNum);


	/**
	 * 通过sampleId获取检验结果
	 * @param sample_id
	 * @return
	 */
	Detection selectBySampleId(String sample_id);

	/**
	 * 通过labCode查询得到sampleId
	 * @param labCode
	 * @return
	 */
	Integer selectSampleIdByLabCode(String labCode);
	
	/**
	 * 批量插入数据
	 * @return
	 */
	Integer insertByBatch(List<Detection> list);
	
	/**
	 * 通过labCode和sampleNumber查询得到sampleId
	 * @param labCode
	 * @param sampleNumber
	 * @return
	 */
	Integer selectSampleIdByLabCodeAndSampleNum(@Param("labCode")String labCode,@Param("sampleNumber") String sampleNumber);


	List<String> selectSampleIdList(List<String> detectionList);


	List<Detection> selectDetectionList(List<String> sampleList);
	
	List<Sample> selectSampleList(List<String> sampleList);
	
	List<SampleExtractedDna> selectSampleExtractedDnaList(List<String> sampleList);


	Sample selectSampleById(@Param("sampleId") Long sampleId);


	
}
