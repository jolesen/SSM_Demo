package com.stylefeng.guns.system.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.modular.business.dao.ProjectDao;
import com.stylefeng.guns.modular.business.entity.Project;
import com.stylefeng.guns.modular.business.service.IProjectService;

@Transactional
public class ProjectServiceTest extends BaseJunit {
    @Autowired
   IProjectService projectService;
    
    @Autowired
    ProjectDao projectDao;
    @Before
    public void Login(){
        super.setupMockMvc("","");
    }
    @Test
    public void testService() {
        Project project = projectService.getProjectByName("托普测试");
        System.out.println(project.toString());
    }
}
