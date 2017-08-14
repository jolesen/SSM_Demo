package com.stylefeng.guns.modular.business.service;

import java.util.List;

import com.stylefeng.guns.modular.business.entity.Sample;



public interface IThreadSampleService {
	public String inputSamples(List<Sample> list , int start , int end) throws Exception;

	public void run(List<Sample> list, int start, int end);
}


