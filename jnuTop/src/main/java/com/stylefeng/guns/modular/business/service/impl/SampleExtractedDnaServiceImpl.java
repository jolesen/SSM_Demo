package com.stylefeng.guns.modular.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.stylefeng.guns.modular.business.dao.SampleExtractedDnaDao;
import com.stylefeng.guns.modular.business.entity.SampleExtractedDna;
import com.stylefeng.guns.modular.business.entity.model.SampleAndExtractionDna;
import com.stylefeng.guns.modular.business.service.ISampleExtractedDnaService;

/**
 * 样本提取Service
 *
 * @author
 * @Date 2017-08-24 09:41:37
 */
@Service
public class SampleExtractedDnaServiceImpl implements ISampleExtractedDnaService {

	@Resource
    private SampleExtractedDnaDao sampleExtractedDnaDao;
	
	public void setSampleExtractedDnaDao(SampleExtractedDnaDao sampleExtractedDnaDao) {
		this.sampleExtractedDnaDao = sampleExtractedDnaDao;
	}

	@Override
	public List<SampleAndExtractionDna> list(String condition) {
		List<SampleAndExtractionDna> listSampleAndExtraction = sampleExtractedDnaDao.listSampleDNA(condition);
		return listSampleAndExtraction;  
	}

	@Override
	public List<SampleExtractedDna> listDNA(String condition) {
		List<SampleExtractedDna> listDNA = sampleExtractedDnaDao.listDNA(condition);
		return listDNA;  
	}
	
}
