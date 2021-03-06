package com.stylefeng.guns.modular.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stylefeng.guns.common.exception.RepeatException;
import com.stylefeng.guns.modular.business.dao.SampleDao;
import com.stylefeng.guns.modular.business.dao.TSampleMapper;
import com.stylefeng.guns.modular.business.entity.Sample;
import com.stylefeng.guns.modular.business.service.IThreadSampleService;



@Service
@Transactional
public class ThreadSampleServiceImpl implements IThreadSampleService{
    @Resource
	TSampleMapper sampleMapper;
	
	public void run(List<Sample> list, int start, int end) {
		// TODO Auto-generated method stub
		
	}

	public String inputSamples(List<Sample> list, int start, int end) throws Exception {
		System.out.println("准备插入数据库 start:"+start+" end:"+end);
		// TODO Auto-generated method stub
		int i=0;

	try{
			for(i = start; i<=end ;i++){
				sampleMapper.insert(list.get(i));
			}
		}catch (Exception e) {
			// TODO: handle exception
		    System.out.println("第"+(i+1)+"条记录数据重复，"+e.getMessage());
			throw new RepeatException(i);
		}
		return "SUCCEED";
	}


	
}