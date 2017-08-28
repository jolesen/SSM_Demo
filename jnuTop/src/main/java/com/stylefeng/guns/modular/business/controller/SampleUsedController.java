package com.stylefeng.guns.modular.business.controller;

import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.persistence.model.User;
import com.stylefeng.guns.modular.business.dao.SampleUsedDao;
import com.stylefeng.guns.modular.business.entity.SampleUsed;
import com.stylefeng.guns.modular.business.service.ISampleUsedService;
import com.stylefeng.guns.modular.system.dao.UserMgrDao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 样本领用控制器
 *
 * @author 罗健金
 * @Date 2017-08-24 10:05:50
 */
@Controller
@RequestMapping("/sampleUsed")
public class SampleUsedController extends BaseController {

    @Resource
    private SampleUsedDao sampleUsedDao;
    @Resource
    private UserMgrDao userMgrDao;
    @Resource
    ISampleUsedService sampleUsedService;
    private String PREFIX = "/business/sampleUsed/";

    /**
     * 跳转到样本领用首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "sampleUsed.html";
    }

    /**
     * 跳转到添加样本领用
     */
    @RequestMapping("/sampleUsed_add/{sample_id}")
    public String sampleUsedAdd( @PathVariable Integer sample_id,Model model) {
        model.addAttribute("sampleId", sample_id);
        model.addAttribute("userId", "1");
        User user = userMgrDao.getByAccount("admin");
        model.addAttribute("username", user.getName());
        return PREFIX + "sampleUsed_add.html";
    }

    /**
     * 跳转到修改样本领用
     */
    @RequestMapping("/sampleUsed_update/{sampleUsedId}")
    public String sampleUsedUpdate(@PathVariable Integer sampleUsedId, Model model) {
        return PREFIX + "sampleUsed_edit.html";
    }

    /**
     * 获取样本领用列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<Map<String, Object>> sampleUseds = sampleUsedDao.selectSampleUsedByCondition(condition);
        return sampleUseds;
    }

    /**
     * 新增样本领用
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Valid SampleUsed sampleUsed) {
        sampleUsedService.insertSampleUsed(sampleUsed);
        return SUCCESS_TIP;
    }

    /**
     * 删除样本领用
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete() {
        return SUCCESS_TIP;
    }


    /**
     * 修改样本领用
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update() {
        return super.SUCCESS_TIP;
    }

    /**
     * 样本领用详情
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Object detail() {
        return null;
    }
}
