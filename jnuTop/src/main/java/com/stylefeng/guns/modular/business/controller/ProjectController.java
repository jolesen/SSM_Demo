package com.stylefeng.guns.modular.business.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.stylefeng.guns.common.annotion.log.BussinessLog;
import com.stylefeng.guns.common.constant.Dict;
import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.exception.BussinessException;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.business.dao.ProjectDao;
import com.stylefeng.guns.modular.business.entity.Project;
import com.stylefeng.guns.modular.business.service.IProjectService;

/**
 * 项目管理控制器
 *
 * @author honor
 * @Date 2017-08-17 13:48:19
 */
@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController {

    private String PREFIX = "/business/project/";

    @Resource
    private ProjectDao projectDao;
    @Resource
    private IProjectService projectService;

    

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
     * 跳转到修改页面
     * @author 罗健金
     * @date 2017年8月21日
     * @param projectId
     * @param model
     * @return
     */
    @RequestMapping("/project_update/{projectId}")
    public String projectUpdate(@PathVariable Integer projectId, Model model) {
        Project project = projectDao.selectById(projectId);
        model.addAttribute(project);
        if("1".equals(project.getIsWorkingday())) {
            model.addAttribute("ifWorkingday","工作日");
        }
        if("0".equals(project.getIsWorkingday())) {
            model.addAttribute("ifWorkingday","自然日");
        }
        return PREFIX + "project_edit.html";
    }

    /**
     * 获取项目管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<Map<String,Object>> project = this.projectDao.list(condition);
        return project;
    }

    /**
     * 新增项目管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    @BussinessLog(value = "新增项目",key = "name",dict = Dict.ProjectMap)
    public Object add(Project project) {
      projectService.insertProject(project);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除项目管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    @BussinessLog(value = "删除项目", key = "projectId", dict = Dict.DeleteDict)
    public Object delete(@RequestParam Integer projectId) {
        // 缓存通知名称
        LogObjectHolder.me().set(ConstantFactory.me().getNoticeTitle(projectId));
        if( projectService.deleteById(projectId)) {
            return SUCCESS_TIP;
        }
        return "false";
        
       
    }
    
    /**
     * 暂停项目
     */
    @RequestMapping(value = "/suspend")
    @ResponseBody
    public Object suspend(@RequestParam Integer projectId) {
        try {
            this.projectDao.suspendById(projectId);
            return SUCCESS_TIP;
        } catch (Exception e) {
            e.printStackTrace();
            return "暂停失败";
        }
        
     
    }
    
    /**
     * 恢复项目管理
     * @author 罗健金
     * @date 2017年8月21日
     * @param projectId
     * @return
     */
    @RequestMapping(value = "/restore")
    @ResponseBody
    public Object restore(@RequestParam Integer projectId) {
      
        this.projectDao.restoreById(projectId);
        return SUCCESS_TIP;
    }

   /**
    * 修改项目
    * @author 罗健金
    * @date 2017年8月21日
    * @param project
    * @return
    */
    @RequestMapping(value = "/update")
    @ResponseBody
    @BussinessLog(value = "修改项目",key = "name",dict = Dict.ProjectMap)
    public Object update(Project project) {
        projectService.updateProjectById(project);
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
