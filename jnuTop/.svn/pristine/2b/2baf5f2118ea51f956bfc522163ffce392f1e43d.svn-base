package com.stylefeng.guns.modular.business.controller;



import java.io.IOException;
import java.net.BindException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.exception.BussinessException;
import com.stylefeng.guns.modular.business.service.IExcelModelService;
import com.stylefeng.guns.modular.business.service.ISampleService;

/**
 * 上传excel模板
 * @author zhuangziwei
 *
 */
@Controller
@RequestMapping("/excelModel")
public class ExcelModelController extends BaseController {
	
	  @Resource
	  IExcelModelService excelModelService;
	  
	   /**
	     * 跳转到样本首页
	     */
	    @RequestMapping("")
	    public String index() {
	        return "/upload.html";
	    }

	
	 @RequestMapping(value = "/add")
	 @ResponseBody
	    public Object add(@RequestParam("uploadModel") MultipartFile  uploadFile,String modelName) {
		 	String fileName = uploadFile.getOriginalFilename(); 
		 	super.getHttpServletResponse().setCharacterEncoding("UTF-8");
		 	if(!fileName.endsWith(".xlsx")){
		 		throw new BussinessException(BizExceptionEnum.ERROR_FILE);
		 		
		 	}
		 	try {
				int result = excelModelService.addExcelModel(modelName, uploadFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new BussinessException(BizExceptionEnum.EXCEL_MODEL_INSERT_FILE);
			}
	        return super.SUCCESS_TIP;
	    }
	
	 
}