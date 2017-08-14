package com.stylefeng.guns.modular.business.controller;

import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.exception.NonullException;
import com.stylefeng.guns.core.util.ExcelReader;
import com.stylefeng.guns.modular.business.entity.Sample;
import com.stylefeng.guns.modular.business.service.IThreadSampleService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 样本控制器
 *
 * @author fengshuonan
 * @Date 2017-08-09 14:40:25
 */
@Controller
@RequestMapping("/sample")
public class SampleController extends BaseController {

	private String PREFIX =
			"/business/sample/";
	
	@Autowired
	private IThreadSampleService threadSampleService;

	/**
	 *               跳转到样本首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "sample.html";
	}

	/**
	 * 跳转到添加样本
	 */
	@RequestMapping("/sample_add")
	public String sampleAdd() {
		return PREFIX + "sample_add.html";
	}

	/**
	 * 跳转到附件上传界面
	 */
	@RequestMapping("/sample_uploadFile")
	public String uploadFile() {
		return PREFIX + "sample_uploadFile.html";
	}
	
	/**
	 * 跳转到修改样本
	 */
	@RequestMapping("/sample_update/{sampleId}")
	public String sampleUpdate(@PathVariable Integer sampleId, Model model) {
		return PREFIX + "sample_edit.html";
	}

	/**
	 * 获取样本列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(String condition) {
		return null;
	}

	/**
	 * 新增样本
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add() {
		return super.SUCCESS_TIP;
	}

	
	
	
	
	
	/**
	 * 删除样本
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete() {
		return SUCCESS_TIP;
	}

	/**
	 * 修改样本
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update() {
		return super.SUCCESS_TIP;
	}

	/**
	 * 样本详情
	 */
	@RequestMapping(value = "/detail")
	@ResponseBody
	public Object detail() {
		return null;
	}

	/**
	 * Excel导入
	 * 
	 * @param uploadFile
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 * @throws InvalidFormatException 
	 */
	@RequestMapping(value = "/insertExcel")
	public String insertExcel(MultipartFile uploadFile,HttpServletRequest request)
			throws IllegalStateException, IOException, InvalidFormatException {
		
		System.out.println("=====");
		File file = null;
		Map<String, Object> theResult = new HashMap<String, Object>();

		if (uploadFile != null) {
			file = multipartToFile(uploadFile);
		} else {
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
			
			String result =  threadSampleService.inputSamples(list, 0, list.size() - 1);
		} catch (Exception e) {
			// TODO: handle exception
			theResult.put("result", "FALSE");
			theResult.put("message", "表格有重复的数据");
			return "insertExcelAction";
		}
		theResult.put("result", "SUCCEED");
		theResult.put("message", "导入成功,共导入" + list.size() + "条数据");
		return "insertExcelAction";
	}
	
	
	
	
	/**
	 * 将MultipartFile转成File类型
	 * 
	 * @param multfile
	 *            输入流
	 * @return File文件类型
	 * @throws IOException
	 */
	private File multipartToFile(MultipartFile multfile) throws IOException {
		CommonsMultipartFile cf = (CommonsMultipartFile) multfile;
		// 这个myfile是MultipartFile的
		DiskFileItem fi = (DiskFileItem) cf.getFileItem();
		File file = fi.getStoreLocation();
		return file;
	}

	public IThreadSampleService getThreadSampleService() {
		return threadSampleService;
	}

	public void setThreadSampleService(IThreadSampleService threadSampleService) {
		this.threadSampleService = threadSampleService;
	}
	
	
	
	
	
	
	

}
