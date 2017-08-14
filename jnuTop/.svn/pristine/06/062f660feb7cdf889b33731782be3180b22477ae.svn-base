package com.stylefeng.guns.modular.business.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.stylefeng.guns.common.exception.NonullException;

import com.stylefeng.guns.core.util.ExcelReader;
import com.stylefeng.guns.modular.business.entity.Sample;
import com.stylefeng.guns.modular.business.service.IThreadSampleService;
import com.stylefeng.guns.modular.business.service.impl.ThreadSampleServiceImpl;



public class SampleControl {
	

	public String insertExcel(MultipartFile uploadFile, HttpServletRequest request)
			throws IllegalStateException, IOException, InvalidFormatException{
		File file = null;
		Map<String, Object> theResult = new HashMap<String, Object>();
		
		if(uploadFile != null){
			file = multipartToFile(uploadFile);
		}else{
			theResult.put("result", "FALSE");
			theResult.put("message", "文件为空");
			return "insertExcelAction";
		}
		
		
		
		
		List<Sample> list = new ArrayList<Sample>();
		try {
			list = ExcelReader.read(file);
		} catch (NonullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			theResult.put("result", "FALSE");
			theResult.put("message", e.getErrorInfo());
			return "insertExcelAction";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			theResult.put("result", "FALSE");
			theResult.put("message", "解析文件时发生未知错误");
			return "insertExcelAction";
		}
		try {
			IThreadSampleService threadSampleService = new ThreadSampleServiceImpl();
			String result =	threadSampleService .inputSamples(list, 0, list.size() - 1);
		} catch (Exception e) {
			// TODO: handle exception
			theResult.put("result", "FALSE");
			theResult.put("message", "表格有重复的数据");
			return "insertExcelAction";
		}
		theResult.put("result", "SUCCEED");
		theResult.put("message", "导入成功,共导入"+list.size()+"条数据");
		return "insertExcelAction";
	}
	
	/**
	 * 将MultipartFile转成File类型
	 * @param multfile 输入流
	 * @return  File文件类型
	 * @throws IOException
	 */
	private File multipartToFile(MultipartFile multfile) throws IOException {  
        CommonsMultipartFile cf = (CommonsMultipartFile)multfile;   
        //这个myfile是MultipartFile的  
        DiskFileItem fi = (DiskFileItem) cf.getFileItem();  
        File file = fi.getStoreLocation();  
        return file;  
    }  
}
