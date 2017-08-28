package com.stylefeng.guns.system;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import com.stylefeng.guns.base.BaseJunit;




/**
 * 测试部门权限
 * 
 * @author zmj
 * @since 2017年8月22日下午2:06:21
 */
public class DeptPermissionTest extends BaseJunit{

	
	@Before
	public void login() {
		super.setupMockMvc("test","111111");
	}
	
	/**
	 * 测试冻结用户  测试失败，部门权限不足，对系统管理模块进行操作需要超级管理员角色
	 * @author zmj
	 * @since 2017年8月22日下午2:06:26
	 */
	@Test
	public void testMgrFreeze() {
		 try {
			mockMvc.perform(get("/mgr/freeze")
			            .param("userId", "44")
			    ).andDo(print())
			    .andExpect(status().isOk())
			    ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试删除菜单
	 * @author zmj
	 * @since 2017年8月22日下午2:06:31
	 */
	@Test
	public void testMenuRemove(){
		try{
			mockMvc.perform(get("/menu/remove")
					.param("menuId","191")				
					).andDo(print())
			.andExpect(status().isOk());
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	



}
