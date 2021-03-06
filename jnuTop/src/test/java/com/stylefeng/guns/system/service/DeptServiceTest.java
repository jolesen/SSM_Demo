package com.stylefeng.guns.system.service;

import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.modular.system.dao.MenuDao;
import com.stylefeng.guns.modular.system.service.IDeptService;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.assertEquals;
import java.util.List;


/**
 * 对于DeptService中自定义方法的测试类
 * @author zmj
 * @since 2017/8/22
 */
@Transactional
public class DeptServiceTest extends BaseJunit {
	
	@Autowired
	IDeptService deptService;
	
    @Autowired
    MenuDao menuDao;
    
    /**
     * 登录
     * @author zmj
     * @since 2017年8月22日下午2:29:36
     */
    @Before
    public void Login(){
    	super.setupMockMvc("","");
    }
    
    
    /**
     * 对于service中setAuthority方法的测试，成功插入
     * @author zmj
     * @since 2017/8/22
     */
    @Test
    public void setAuthorityTest() {

        Integer deptId = 25;
        String ids = "3";

        deptService.setAuthority(deptId, ids);
      
        List<Integer> list = menuDao.getMenuIdsByDeptId(25);
        assertEquals(1, list.size());
        
    }
}
