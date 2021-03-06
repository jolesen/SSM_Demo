package com.stylefeng.guns.modular.business.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.stylefeng.guns.common.constant.tips.ErrorTip;
import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.exception.BussinessException;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.modular.business.dao.DetectionDao;
import com.stylefeng.guns.modular.business.entity.Detection;
import com.stylefeng.guns.modular.business.entity.model.DnaExcel;
import com.stylefeng.guns.modular.business.service.IDetectionService;

/**
 * 检测结果控制器
 *
 * @author
 * @Date 2017-08-23 15:52:04
 */
@Controller
@RequestMapping("/detection")
public class DetectionController extends BaseController {
	@Resource
	private IDetectionService detectionService;
	@Resource
	private DetectionDao detectionDao;
	
    private String PREFIX = "/business/detection/";

    /**
     * 跳转到检测结果首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "detection.html";
    }

    /**
     * 跳转到添加检测结果
     */
    @RequestMapping("/detection_add")
    public String detectionAdd() {
        return PREFIX + "detection_add.html";
    }

    /**
     * 跳转到修改检测结果
     */
    @RequestMapping("/detection_update/{detectionId}")
    public String detectionUpdate(@PathVariable Integer detectionId, Model model) {
    	Detection detection = detectionService.findDetectionById(detectionId);
        model.addAttribute(detection);
        return PREFIX + "detection_edit.html";
    }

    /**
     * 获取检测结果列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	List<Map<String, Object>> detections = detectionDao.selectDetectionsByCondition(condition);
        return detections;
    }

    /**
     * 新增检测结果
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Valid Detection detection, BindingResult bindingResult,HttpServletRequest request) {

    	 if (bindingResult.hasErrors()) {
             throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
         }
         detection.setDetectionId(null);
         //当前操作者
         Integer userId = ShiroKit.getUser().getId();
         detection.setDetectorId(userId.toString());
         try {
             Integer insertedRow = detectionDao.insert(detection);
             if (insertedRow <= 0) {
                 throw new BussinessException(BizExceptionEnum.SERVER_ERROR);
             }
         } catch (Exception e) {
             throw new BussinessException(BizExceptionEnum.UNABLE_ERROR);
         }
        return super.SUCCESS_TIP;
    }

    /**
     * 删除检测结果
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String[] detectionId) {
    	System.out.println(detectionId[0]);
    	detectionService.deleteById(detectionId);
        return SUCCESS_TIP;
    }
    
	/**
	 * 跳转到Excel导入页面
	 *
	 * @return
	 */
	@RequestMapping("/detection_uploadFile")
	public String sampleUpload() {
		return PREFIX + "detection_uploadFile.html";
	}


    /**
     * 修改检测结果
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Detection detection) {
    	detectionDao.update(detection);
        return super.SUCCESS_TIP;
    }

    /**
     * 检测结果详情
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Object detail() {
        return null;
    }
    
    
    /**
     * excel导入
     * @param uploadFile 上传文件
     * @param importFieldName  需要导入的excel列名
     * @param importFieldMethod 需要导入的excel列名对应的set方法
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    @ResponseBody
    public Object  insertExcel(@RequestParam("uploadFile") MultipartFile  uploadFile,String importFieldName,
    		String importFieldMethod) throws Exception{
    	Object tip=new ErrorTip(500, "服务器异常");
    	try{
    		tip = detectionService.importExcel(uploadFile,importFieldName,importFieldMethod);
    	}catch(Exception e){
    		System.out.println(e);
    	}
    	
    	return tip;
    }
    
    /**
     * excel导出                          
     * @param ids 需要导出的检测结果id
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/exportExcel")
    @ResponseBody
    public void exportExcel(String[] ids,HttpServletResponse response) throws IOException{

    	try{
			response.setContentType("application/x-execl");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String("检测列表.xlsx".getBytes(), "ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			List<DnaExcel> dnaExcelList = detectionService.dnaDetectionId(ids);
			detectionService.exportExcel(dnaExcelList, outputStream);
			if(outputStream != null){
				outputStream.close();
			}
    	}catch (Exception e){
    		e.printStackTrace();
    	}
    }
    

    
}
