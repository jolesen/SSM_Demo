package com.stylefeng.guns.system.service;

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

/**
 * 对于DeptService中自定义方法的测试类
 * @author 梁俊鹏
 * @since 2017/8/18
 */
@Transactional
public class DeptServiceTest extends BaseJunit {

    @Resource
    IDeptService deptService;

    @Autowired
    DeptMenuRelationMapper relationMapper;


    /**
     * 对于service中setAuthority方法的测试，成功插入
     * @author 梁俊鹏
     * @since 2017/8/18
     */
    @Test
    public void setAuthorityTest() {

        Integer deptId = 25;
        String ids = "3";

        Map<String, Object> map = new HashMap<>(2);
        map.put("dept_id", "25");
        map.put("menu_id", "3");
        deptService.setAuthority(deptId, ids);
        List<DeptMenuRelation> list = relationMapper.selectByMap(map);

        org.junit.Assert.assertEquals(list.size(), 1);
    }
}
