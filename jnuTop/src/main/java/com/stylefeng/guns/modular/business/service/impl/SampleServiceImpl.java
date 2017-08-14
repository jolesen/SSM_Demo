package com.stylefeng.guns.modular.business.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.druid.sql.ast.statement.SQLIfStatement.Else;
import com.alibaba.druid.sql.ast.statement.SQLIfStatement.ElseIf;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.exception.BussinessException;
import com.stylefeng.guns.modular.business.dao.SampleDao;
import com.stylefeng.guns.modular.business.entity.Sample;
import com.stylefeng.guns.modular.business.service.ISampleService;


/**
 * 样本Service
 *
 * @author fengshuonan
 * @Date 2017-08-09 14:40:25
 */
@Service
public class SampleServiceImpl implements ISampleService {
	
	@Resource
    private SampleDao sampleDao;

	@Override
	public void add(Sample sample) {
		
		try {
			sampleDao.insert(sample);
		} catch (Exception e) {
			throw new BussinessException(BizExceptionEnum.SERVER_ERROR);
		}
		
	}

	@Override
	public String checkInput(Sample sample){
		if(sample.getSampleStorage() == null){
			return "sampleStorage";
		}
		else if (sample.getDetectionItem() == null) {
			return "detectionItem";
		}else if (sample.getLabCode() == null) {
			return "labCode";
		}else if(sample.getSampleNumber() == null){
			return "sampleNumber";
		}else if (sample.getSubjectName() == null) {
			return "subjectName";
		}
		return "success";
	}

}
