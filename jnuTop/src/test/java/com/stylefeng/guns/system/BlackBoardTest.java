package com.stylefeng.guns.system;

import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.modular.system.dao.NoticeDao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * 首页通知展示测试
 *
 * @author fengshuonan
 * @date 2017-05-21 15:02
 */
public class BlackBoardTest extends BaseJunit {

    @Autowired
    NoticeDao noticeDao;

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
    public void blackBoardTest() {
        List<Map<String, Object>> notices = noticeDao.list(null);
        assertTrue(notices.size() > 0);
    }
}
