package com.stylefeng.guns.modular.business.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.common.constant.tips.Tip;
import com.stylefeng.guns.common.exception.RepeatException;
import com.stylefeng.guns.modular.business.entity.Sample;

/**
 * 样本Service
 *
 * @author djb
 * @Date 2017-08-09 14:40:25
 */
public interface ISampleService {
	/**
	 * 添加样品
	 * 
	 * @param sample
	 */
	public void add(Sample sample);

	/**
	 * 通过传入的id批量删除样本信息
	 * 
	 * @param sampleId
	 */
	public void deleteById(String[] sampleId);

	/**
	 * 校验输入是否有空
	 * 
	 * @param sample
	 * @return
	 */
	public String checkInput(Sample sample);

	/**
	 * 通过id查询样本信息
	 * 
	 * @param sample_id
	 * @return
	 */
	Sample findSampleById(Integer sample_id);

	/**
	 * 通过id更新样本信息
	 * 
	 * @param sample
	 */
	int updateSample(Sample sample);

}
