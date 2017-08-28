package com.stylefeng.guns.modular.business.controller;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.modular.business.entity.model.SampleAndExtractionDna;
import com.stylefeng.guns.modular.business.service.ISampleExtractedDnaService;

/**
 * 样本提取控制器
 *
 * @author
 * @Date 2017-08-24 09:41:37
 */
@Controller
@RequestMapping("/sampleExtractedDna")
public class SampleExtractedDnaController extends BaseController {

    private String PREFIX = "/business/sampleExtractedDna/";

    @Resource
    private ISampleExtractedDnaService sampleExtractedDnaService;
    
    public void setISampleExtractedDnaService(ISampleExtractedDnaService iSampleExtractedDnaService) {
		sampleExtractedDnaService = iSampleExtractedDnaService;
	}
    
    /**
     * 跳转到样本提取首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "sampleExtractedDna.html";
    }

    /**
     * 跳转到添加样本提取
     */
    @RequestMapping("/sampleExtractedDna_add")
    public String sampleExtractedDnaAdd() {
        return PREFIX + "sampleExtractedDna_add.html";
    }

    /**
     * 跳转到修改样本提取
     */
    @RequestMapping("/sampleExtractedDna_update/{sampleExtractedDnaId}")
    public String sampleExtractedDnaUpdate(@PathVariable Integer sampleExtractedDnaId, Model model) {
        return PREFIX + "sampleExtractedDna_edit.html";
    }

    /**
     * 获取样本提取列表 
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	//如果没有传任何参数的话，默认是null，那么在dao层查询sql时候是查不出任何东西的，处理成空字符串即可。
    	if(condition==null){
    		condition="";
    	}
    	List<SampleAndExtractionDna> list = sampleExtractedDnaService.list(condition);
    	//用注解方式查询的结果是以类的属性转换为json，若在xml中声明返回类型是map的话，是以数据库字段转换为json,参考sampleExtractedDna.js中初始化表格列的field属性。
    	return list;
    }

    /**
     * 新增样本提取（不需要使用到）
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add() {
        return super.SUCCESS_TIP;
    }

    /**
     * 删除样本提取
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete() {
        return SUCCESS_TIP;
    }


    /**
     * 修改样本提取
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update() {
        return super.SUCCESS_TIP;
    }

    /**
     * 样本提取详情
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Object detail() {
        return null;
    }
}
