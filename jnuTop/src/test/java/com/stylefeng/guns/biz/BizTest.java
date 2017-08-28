package com.stylefeng.guns.biz;

import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.modular.biz.service.ITestService;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 业务测试
 *
 * @author fengshuonan
 * @date 2017-06-23 23:12
 */
public class BizTest extends BaseJunit {

    @Autowired
    ITestService testService;

    /**
     * 登录
     * @author zmj
     * @since 2017年8月22日下午2:29:36
     */
    @Before
    public void Login(){
    	super.setupMockMvc("","");
    }
    
    @Test
    public void test() {
        //testService.testGuns();

        testService.testBiz();

        //testService.testAll();
    }
}
