package com.stylefeng.guns.system;

import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.modular.system.dao.MenuDao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 对于DeptController中自定义方法的测试类
 * @author zmj
 * @since 2017/8/22
 */
@Transactional
public class DeptControllerTest extends BaseJunit {

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
     * 对于setAuthority方法的成功插入的测试
     * @author zmj
     * @throws Exception
     */
    @Test
    public void setAuthorityTest() throws Exception{

        mockMvc.perform(get("/dept/setAuthority")
                .param("deptId", "25")
                .param("ids", "107")
        ).andDo(print())
        .andExpect(status().isOk())
        ;
        
        List<Integer> list = menuDao.getMenuIdsByDeptId(25);
        assertEquals(1, list.size());
    }
}
