package com.stylefeng.guns.modular.business.controller;



import java.io.IOException;
import java.io.OutputStream;
import java.net.BindException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.builder.StandardToStringStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.exception.BussinessException;
import com.stylefeng.guns.modular.business.entityenum.EntityToChina;
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
	 
	 /**
	  * processName指某个流程的名称，例如ProcessDnaExtract   
	  * String[] detectionItem,String[] labCode,String[] sampleNumber是前端提交过来的可选参数，
	  * detectionItem指检测项目名称   labcode指的是实验室的编码  samplenumber指的是样本编码
	  * @param processName
	  * @param detectionItem
	  * @param labCode
	  * @param sampleNumber
	  * @throws ClassNotFoundException
	  * @throws InstantiationException
	  * @throws IllegalAccessException
	  */
	 @RequestMapping(value = "/download")
	 	public void download(String processName,String[] detectionItem,String[] labCode,String[] sampleNumber) 
	 			throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		XSSFWorkbook  wb = excelModelService.createExcel(processName,  detectionItem, labCode, sampleNumber) ;
		HttpServletResponse response = super.getHttpServletResponse();
		response.setCharacterEncoding("UTF-8");
		  try {
		    response.setHeader("Content-Disposition", "attachment; filename="+processName+".xlsx");
		    response.setContentType("application/vnd.ms-excel; charset=utf-8") ;
		    OutputStream out = response.getOutputStream() ;
		    wb.write(out) ;
		    out.flush();
		    out.close();
		  } catch (IOException e) {
		    e.printStackTrace();
		  } 
	 }
	 
}