package com.stylefeng.guns.modular.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stylefeng.guns.modular.business.dao.SampleDao;
import com.stylefeng.guns.modular.business.entity.Sample;
import com.stylefeng.guns.modular.business.service.IThreadSampleService;

@Service("threadSampleService")
@Transactional
public class ThreadSampleServiceImpl implements IThreadSampleService{

	@Resource
	SampleDao sampleDao;
	
	
	public String inputSamples(List<Sample> list , int start , int end) throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		try{
			for(i = start; i<=end ;i++){
				//sampleDao.saveSample(list.get(i));
				sampleDao.insertAllColumn(list.get(i));
			}
		}catch (Exception e) {
			// TODO: handle exception
			throw new Exception("第"+(i+1)+"有重复数据");
		}
		return "SUCCEED";
	
	}

	
	public void run(List<Sample> list, int start, int end){
		// TODO Auto-generated method stub
		
	}
}
