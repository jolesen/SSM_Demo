package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.common.annotion.log.BussinessLog;
import com.stylefeng.guns.common.constant.Dict;
import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.common.constant.tips.SuccessTip;
import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.exception.BussinessException;
import com.stylefeng.guns.common.node.ZTreeNode;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.Convert;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.dao.DeptDao;
import com.stylefeng.guns.modular.system.dao.NoticeDao;
import com.stylefeng.guns.modular.system.dao.NoticeUserRelationDao;
import com.stylefeng.guns.modular.system.dao.RoleDao;
import com.stylefeng.guns.modular.system.dao.UserMgrDao;
import com.stylefeng.guns.modular.system.service.INoticeService;
import com.stylefeng.guns.modular.system.warpper.NoticeWrapper;
import com.stylefeng.guns.common.persistence.dao.NoticeMapper;
import com.stylefeng.guns.common.persistence.dao.UserMapper;
import com.stylefeng.guns.common.persistence.model.Notice;
import com.stylefeng.guns.common.persistence.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 通知控制器
 *
 * @author zmj
 * @since 2017年8月23日下午3:13:08
 */
@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {

	private String PREFIX = "/system/notice/";

	@Resource
	private NoticeMapper noticeMapper;

	@Resource
	private NoticeDao noticeDao;

	@Resource
	private INoticeService noticeService;

	@Resource
	private NoticeUserRelationDao NoticeUserRelationDao;
	
	@Resource
	private DeptDao deptDao;
	
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private RoleDao roleDao;
	
	@Resource
	private UserMgrDao userDao;
	
	/**
	 * 跳转到通知列表首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "notice.html";
	}

	/**
	 * 跳转到添加通知
	 */
	@RequestMapping("/notice_add")
	public String noticeAdd() {
		return PREFIX + "notice_add.html";
	}

	/**
	 * 跳转到修改通知
	 */
	@RequestMapping("/notice_update/{noticeId}")
	public String noticeUpdate(@PathVariable Integer noticeId, Model model) {
		Notice notice = this.noticeMapper.selectById(noticeId);
		model.addAttribute("notice", notice);
		LogObjectHolder.me().set(notice);
		return PREFIX + "notice_edit.html";
	}

	/**
	 * 跳转到首页通知
	 */
	@RequestMapping("/hello")
	public String hello() {
		List<Map<String, Object>> notices;
		notices = noticeDao.listByUserId(ShiroKit.getUser().getId(), "true");
		super.setAttr("noticeList", notices);
		return "/blackboard.html";
	}


	/**
	 * 获取通知列表 超级管理员可以看到所有的通知， 非超级管理员只能看到与自己有关的通知
	 * 
	 * @author zmj
	 * @since 2017年8月24日下午1:48:54
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(String condition) {
		List<Map<String, Object>> list;
		if (ShiroKit.isAdmin()) {
			list = this.noticeDao.list(condition);
		} else {
			list = this.noticeDao.listByUserId(ShiroKit.getUser().getId(), "true");
			for (Map<String, Object> map : list) {
				map.put("user_id", ShiroKit.getUser().getId().toString());
			}
		}
		return super.warpObject(new NoticeWrapper(list));
	}

	/**
	 * 新增通知
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	@BussinessLog(value = "新增通知", key = "title", dict = Dict.NoticeMap)
	public Object add(Notice notice,String depts,String roles,String users) {
		if (ToolUtil.isOneEmpty(notice, notice.getTitle(), notice.getContent())) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}

		notice.setCreater(ShiroKit.getUser().getId());
		notice.setCreatetime(new Date());
		notice.insert();
		
		List<Integer> deptList = stringToList(depts);
		List<Integer> userList = stringToList(users);
		List<Integer> roleList = stringToList(roles);
		
		userList.add(ShiroKit.getUser().getId());
		noticeService.addRelation(notice.getId(), deptList, userList, roleList);
		
		return super.SUCCESS_TIP;
	}

	/**
	 * 删除通知
	 * 
	 * @author zmj
	 * @since 2017年8月24日下午1:52:08
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	@BussinessLog(value = "删除通知", key = "noticeId", dict = Dict.DeleteDict)
	public Object delete(@RequestParam Integer noticeId) {

		// 缓存通知名称
		LogObjectHolder.me().set(ConstantFactory.me().getNoticeTitle(noticeId));

		// 检测是否发布者
		Notice notice = this.noticeMapper.selectById(noticeId);

		// 超级管理员或者发布者可以彻底删除通知,非超级管理员只能删除通知与自己的联系
		if (ShiroKit.getUser().getId() == notice.getCreater() || ShiroKit.isAdmin()) {
			this.noticeMapper.deleteById(noticeId);
			this.NoticeUserRelationDao.deleteRelationByNoticeId(noticeId);
			return SUCCESS_TIP;
		} else {
			this.NoticeUserRelationDao.deleteRelationByUserId(ShiroKit.getUser().getId(),noticeId);
			return SUCCESS_TIP;
			//throw new BussinessException(BizExceptionEnum.NOTICE_DELETE_ERROR);
		}

	}

	/**
	 * 修改通知
	 * 
	 * @author zmj
	 * @since 2017年8月24日下午1:51:59
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	@BussinessLog(value = "修改通知", key = "title", dict = Dict.NoticeMap)
	public Object update(Notice notice) {
		if (ToolUtil.isOneEmpty(notice, notice.getId(), notice.getTitle(), notice.getContent())) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}

		// 超级管理员或者发布者可以修改通知
		if (ShiroKit.getUser().getId() == notice.getCreater() || ShiroKit.isAdmin()) {
			Notice old = this.noticeMapper.selectById(notice.getId());
			old.setTitle(notice.getTitle());
			old.setContent(notice.getContent());
			old.updateById();
			return super.SUCCESS_TIP;
		} else {
			throw new BussinessException(BizExceptionEnum.NOTICE_EDIT_ERROR);
		}

	}

	/**
	 * 
	 * 将通知标记为已读
	 * 
	 * @param noticeId
	 *            通知Id
	 * @return
	 * @author zmj
	 * @since 2017年8月25日上午11:32:50
	 */
	@RequestMapping(value = "/hasRead")
	@ResponseBody
	public Object hasRead(Integer noticeId) {
		noticeDao.updateRead(ShiroKit.getUser().getId(), noticeId, 1);
		return SUCCESS_TIP;
	}

	/**
	 * 将通知标记为未读
	 * 
	 * @param noticeId
	 *            通知Id
	 * @return
	 * @author zmj
	 * @since 2017年8月25日上午10:58:36
	 */
	@RequestMapping(value = "/unRead")
	@ResponseBody
	public Object unRead(Integer noticeId) {
		noticeDao.updateRead(ShiroKit.getUser().getId(), noticeId, 0);
		return SUCCESS_TIP;
	}
	
	/**
     * 获取部门的tree列表
     */
    @RequestMapping(value = "/deptTree")
    @ResponseBody
    public List<ZTreeNode> deptTree() {
        List<ZTreeNode> tree = this.deptDao.tree();
        tree.add(ZTreeNode.createParent());
        return tree;
    }
  
    /**
     * 
     * 获取角色的tree列表
     */
    @RequestMapping(value = "roleTree")
    @ResponseBody
    public List<ZTreeNode> roleTree(){
    	List<ZTreeNode> treeNodes =this.roleDao.roleTreeList();
    	return treeNodes;
    }
    
    /**
     * 获取用户的tree列表
     */
    @RequestMapping(value="userTree")
    @ResponseBody
    public List<ZTreeNode> userTree(){
    	List<ZTreeNode> userTreeNodes = userDao.userTreeList();
    	for (ZTreeNode zTreeNode : userTreeNodes) {
			zTreeNode.setpId(0);
			zTreeNode.setIsOpen(true);
		}
    	return userTreeNodes;
    }
    
    /**
     * String转List<Integer>
     * @author zmj
     * @since 2017年8月31日下午2:43:23
     * 
     */
    private List<Integer> stringToList(String str){
    	Integer[] strArray = Convert.toIntArray(str);
		List<Integer> List = new ArrayList<Integer>();
		for (int i = 0; i < strArray.length; i++) {
			List.add(strArray[i]);
		}
		return List;
    }
}
