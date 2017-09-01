package com.stylefeng.guns.modular.business.service;

import java.util.List;

import com.stylefeng.guns.modular.business.entity.Sample;
import com.stylefeng.guns.modular.business.entity.SampleExtractedDna;
import com.stylefeng.guns.modular.business.entity.model.SampleAndExtractionDna;

/**
 * 样本提取Service
 *
 * @author
 * @Date 2017-08-24 09:41:37
 */
public interface ISampleExtractedDnaService {

	List<SampleAndExtractionDna> list(String condition);

	List<SampleExtractedDna> listDNA(String condition);

	Integer deleteOne(String sampleExtractedDnaId);

	SampleAndExtractionDna selectOneById(Integer sampleExtractedDnaId);

	void updateOne(SampleAndExtractionDna sampleAndExtractionDna);

}
