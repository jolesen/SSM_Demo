package com.stylefeng.guns.system.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.modular.system.service.INoticeService;

public class NoticeServiceTest extends BaseJunit{

	@Autowired
	INoticeService noticeService;
	
	@Before
	public void login(){
		super.setupMockMvc("", "");
	}
	
	@Test
	public void relationTest(){
		Integer noticeId = 6;
		List<Integer> depts =new ArrayList<Integer>();
		List<Integer> users =new ArrayList<Integer>();
		List<Integer> roles =new ArrayList<Integer>();
		
		depts.add(25);
		depts.add(26);
		users.add(50);
		users.add(1);
		noticeService.addRelation(noticeId, depts, users, roles);
	}

}
