package com.stylefeng.guns.system.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.common.node.ZTreeNode;
import com.stylefeng.guns.modular.system.dao.MenuDao;

public class MenuDaoTest extends BaseJunit{

	@Autowired
	MenuDao menuDao;
	
	@Before
	public void setUp() throws Exception {
		super.setupMockMvc("", "");
	}

	@Test
	public void testMenuTreeList() {
		List<ZTreeNode> treeNodes = menuDao.menuTreeList();
		for (ZTreeNode node : treeNodes) {
			System.out.println(node.getName()+node.getpId()+node.getIsOpen()+node.getChecked());
			
		}
	}

}
