package com.stylefeng.guns.system;

import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.common.persistence.dao.DeptMenuRelationMapper;
import com.stylefeng.guns.common.persistence.model.DeptMenuRelation;
import com.stylefeng.guns.modular.system.service.IDeptService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 对于DeptController中自定义方法的测试类
 * @author 梁俊鹏
 * @since 2017/8/18
 */
@Transactional
public class DeptControllerTest extends BaseJunit {

    @Resource
    IDeptService deptService;

    @Autowired
    DeptMenuRelationMapper relationMapper;


    /**
     * 对于setAuthority方法的成功插入的测试
     * @author 梁俊鹏
     * @throws Exception
     */
    @Test
    public void setAuthorityTest() throws Exception{
        DeptMenuRelation relation_test = new DeptMenuRelation();
        relation_test.setDeptid(25);
        relation_test.setMenuid(3);

        mockMvc.perform(get("/dept/setAuthority")
                .param("deptId", relation_test.getDeptid().toString())
                .param("ids", relation_test.getMenuid().toString())
        ).andDo(print())
        .andExpect(status().isOk())
        ;

        Map<String, Object> map = new HashMap<>(2);
        map.put("dept_id", relation_test.getDeptid().toString());
        map.put("menu_id", relation_test.getMenuid().toString());
        List<DeptMenuRelation> list = relationMapper.selectByMap(map);
        assertEquals(1, list.size());
    }
}
