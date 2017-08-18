package com.stylefeng.guns.modular.business.controller;

import com.stylefeng.guns.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 项目管理控制器
 *
 * @author
 * @Date 2017-08-17 13:48:19
 */
@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController {

    private String PREFIX = "/business/project/";

    /**
     * 跳转到项目管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "project.html";
    }

    /**
     * 跳转到添加项目管理
     */
    @RequestMapping("/project_add")
    public String projectAdd() {
        return PREFIX + "project_add.html";
    }

    /**
     * 跳转到修改项目管理
     */
    @RequestMapping("/project_update/{projectId}")
    public String projectUpdate(@PathVariable Integer projectId, Model model) {
        return PREFIX + "project_edit.html";
    }

    /**
     * 获取项目管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return null;
    }

    /**
     * 新增项目管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add() {
        return super.SUCCESS_TIP;
    }

    /**
     * 删除项目管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete() {
        return SUCCESS_TIP;
    }


    /**
     * 修改项目管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update() {
        return super.SUCCESS_TIP;
    }

    /**
     * 项目管理详情
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Object detail() {
        return null;
    }
}
