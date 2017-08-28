package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.persistence.dao.BizRecordMapper;
import com.stylefeng.guns.common.persistence.model.BizRecord;
import com.stylefeng.guns.modular.system.service.BizRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务记录控制器
 *
 * @author
 * @Date 2017-08-21 17:09:28
 */
@Controller
@RequestMapping("/record")
public class RecordController extends BaseController {


    private String PREFIX = "/system/record/";

    @Resource
    private BizRecordService bizRecordService;

    @RequestMapping(value = "/detailPage/{bizType}/{bizId}")
    public String detailPage(@PathVariable("bizType") String bizType, @PathVariable("bizId") String bizId,Model model){
        model.addAttribute("bizType",bizType);
        model.addAttribute("bizId",bizId);
        return PREFIX + "record_detail.html";
    }

    /**
     * 业务记录详情
     */
    @RequestMapping(value = "/detail/{bizType}/{bizId}")
    @ResponseBody
    public Object detail(@PathVariable("bizType") String bizType, @PathVariable("bizId") String bizId) {
        List<Map<String, Object>> records = bizRecordService.list(bizType, bizId);
        return records;
    }

    /**
     * 跳转到业务记录首页
     */
    /*@RequestMapping("")
    public String index() {
        return PREFIX + "record.html";
    }*/

    /**
     * 修改业务记录
     */
    /*@RequestMapping(value = "/update")
    @ResponseBody
    public Object update() {
        return super.SUCCESS_TIP;
    }*/

    /**
     * 跳转到添加业务记录
     */
    /*@RequestMapping("/record_add")
    public String recordAdd() {
        return PREFIX + "record_detail.html";
    }*/

    /**
     * 跳转到修改业务记录
     */
    /*@RequestMapping("/record_update/{recordId}")
    public String recordUpdate(@PathVariable Integer recordId, Model model) {
        return PREFIX + "record_edit.html";
    }*/

    /**
     * 获取业务记录列表
     */
    /*@RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return null;
    }*/

    /**
     * 新增业务记录
     */
    /*@RequestMapping(value = "/add")
    @ResponseBody
    public Object add() {
        return super.SUCCESS_TIP;
    }*/

    /**
     * 删除业务记录
     */
    /*@RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete() {
        return SUCCESS_TIP;
    }*/
}
