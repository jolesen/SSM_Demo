package com.stylefeng.guns.modular.business.service;

import org.springframework.stereotype.Service;

import com.stylefeng.guns.modular.business.entity.Project;

/**
 * 项目管理Service
 *
 * @author
 * @Date 2017-08-17 13:48:19
 */
@Service
public interface IProjectService {
    /**
     * 通过project 的 id进行修改数据
     * @author 罗健金
     * @date 2017年8月21日
     * @param project
     * @return
     */
    boolean updateProjectById(Project project);
/**
 * 进行项目的插入,这里需要一些数据校验以及检测,比如将"工作日"转成"1"传入数据库中保存
 * @author 罗健金
 * @date 2017年8月21日
 * @param project
 */
    void insertProject(Project project);
    /**
     * 通过id得到project类
     * @author 罗健金
     * @date 2017年8月22日
     * @param id
     * @return
     */
    Project getProjectById(Integer id);
    /**
     * 通过id删除项目,这里需要检测项目内是否有别的数据,如果有则不能删除,并返回提示
     * @author 罗健金
     * @date 2017年8月22日
     * @param projectId
     * @return
     */
    boolean deleteById(Integer projectId);
    /**
     * 通过名字得到project类
     * @author 罗健金
     * @date 2017年8月23日
     * @param name
     * @return
     */
    Project getProjectByName(String name);
}
