package com.stylefeng.guns.base;

import com.stylefeng.guns.GunsApplication;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

/**
 * 基础测试类
 *
 * @author stylefeng
 * @Date 2017/5/21 16:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GunsApplication.class)
@WebAppConfiguration
@Transactional // 打开的话测试之后数据可自动回滚
public class BaseJunit {

	@Autowired
	protected WebApplicationContext webApplicationContext;
	protected MockMvc mockMvc;
	protected MockHttpSession mockHttpSession;

	/**
	 * 
	 * 当参数为空登录admin用户
	 * 当参数不为空时，登录参数设定参数
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * 
	 * @author zmj
	 * @since 2017年8月22日下午2:16:09
	 * 
	 * 
	 */
	
	public void setupMockMvc(String username, String password) {

		UsernamePasswordToken token;
		/*
		 * 模拟登录的代码，参考LoginController
		 */
		mockHttpSession = new MockHttpSession(webApplicationContext.getServletContext());
		Subject subject = ShiroKit.getSubject();
		if (username != "" && password != "") {
			token = new UsernamePasswordToken(username, password.toCharArray());
		} else {
			token = new UsernamePasswordToken("admin", "111111".toCharArray());
		}
		subject.login(token);

		ShiroUser shiroUser = ShiroKit.getUser();
		mockHttpSession.setAttribute("shiroUser", shiroUser);
		mockHttpSession.setAttribute("username", shiroUser.getAccount());
		subject.getSession().setAttribute("sessionFlag", true);
	}

	@Before
	public void initDatabase() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
}
