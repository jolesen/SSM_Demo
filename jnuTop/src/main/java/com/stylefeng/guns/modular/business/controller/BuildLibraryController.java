package com.stylefeng.guns.modular.business.controller;

import com.stylefeng.guns.common.annotion.Permission;
import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.modular.business.dao.BuildLibraryDao;
import com.stylefeng.guns.modular.business.entity.LibraryBuild;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 文库制备录入控制器
 *
 * @author zmj
 * @Date 2017-08-29 11:45:54
 */
@Controller
@RequestMapping("/BuildLibrary")
public class BuildLibraryController extends BaseController {

    private String PREFIX = "/business/BuildLibrary/";

    @Resource
    private BuildLibraryDao buildLibraryDao;
    
    /**
     * 跳转到文库制备录入首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "BuildLibrary.html";
    }

    /**
     * 跳转到添加文库制备录入
     */
    @RequestMapping("/BuildLibrary_add")
    public String BuildLibraryAdd() {
        return PREFIX + "BuildLibrary_add.html";
    }

    /**
     * 跳转到修改文库制备录入
     */
    @RequestMapping("/BuildLibrary_update/{BuildLibraryId}")
    public String BuildLibraryUpdate(@PathVariable Integer BuildLibraryId, Model model) {
    	LibraryBuild libraryBuild = buildLibraryDao.selectById(BuildLibraryId);
    	//System.out.println(libraryBuild.getSampleId());
    	model.addAttribute(libraryBuild);
        return PREFIX + "BuildLibrary_edit.html";
    }

    /**
     * 获取文库制备录入列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return buildLibraryDao.selectList(null);
    }

    /**
     * 新增文库制备录入
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    @Permission
    public Object add(LibraryBuild libraryBuild) {
    	buildLibraryDao.insert(libraryBuild);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除文库制备录入
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(Integer BuildLibraryId) {
    	buildLibraryDao.deleteById(BuildLibraryId);
        return SUCCESS_TIP;
    }


    /**
     * 修改文库制备录入
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(LibraryBuild libraryBuild) {
    	//System.out.println(libraryBuild);
    	libraryBuild.updateAllColumnById();
        return super.SUCCESS_TIP;
    }

    /**
     * 文库制备录入详情
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Object detail() {
        return null;
    }
}
