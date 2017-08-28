package com.stylefeng.guns.modular.business.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.modular.business.entity.Sample;
import com.stylefeng.guns.modular.business.entity.SampleExtractedDna;
import com.stylefeng.guns.modular.business.entity.model.SampleAndExtractionDna;

/**
 * 
 * ClassName: SampleExtractedDnaDao <br/> 
 * Function: TODO 使用注解的方式实现接口方法。 <br/> 
 * Reason: TODO 简洁. <br/> 
 * date: 2017年8月24日 上午 09:41:37 <br/> 
 * 
 * @author john 
 * @version  
 * @since JDK 1.6
 */
public interface SampleExtractedDnaDao extends BaseMapper<SampleExtractedDna>{

	@Select("SELECT * FROM t_sample")
	List<Sample> list(String condition);

	List<SampleExtractedDna> listDNA(String condition);

	//左外连接两个表
	@Select("SELECT * FROM t_sample NATURAL LEFT OUTER JOIN t_sample_extracted_dna WHERE detection_item LIKE CONCAT('%',#{condition},'%') OR lab_code LIKE CONCAT('%',#{condition},'%') OR sample_number LIKE CONCAT('%',#{condition},'%') OR sample_type LIKE CONCAT('%',#{condition},'%') OR subject_name LIKE CONCAT('%',#{condition},'%')  OR extract_people_id LIKE CONCAT('%',#{condition},'%')  OR id LIKE CONCAT('%',#{condition},'%')  OR extract_date LIKE  BINARY CONCAT('%',#{condition},'%')  OR storage_location LIKE CONCAT('%',#{condition},'%') OR nanodrop LIKE CONCAT('%',#{condition},'%') OR qubit LIKE CONCAT('%',#{condition},'%') OR od260280 LIKE CONCAT('%',#{condition},'%') OR od260230 LIKE CONCAT('%',#{condition},'%') OR is_complete LIKE CONCAT('%',#{condition},'%') OR remarks LIKE CONCAT('%',#{condition},'%') AND deleted=0 ")
	List<SampleAndExtractionDna> listSampleDNA(String condition);
}
