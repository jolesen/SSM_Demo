package com.stylefeng.guns.modular.system.service;

import java.util.List;


/**
 * 通知服务
 * 
 * @author zmj
 * @since 2017年8月24日下午2:45:10
 * 
 *
 */
public interface INoticeService {

	/**
	 * @author zmj
	 * @since 2017年8月24日下午2:45:16
	 * @param noticeId 通知Id
	 * @param depts 发送到部门的id集合
	 * @param users 发送到用户的id集合
	 * @param roles 发送到角色的id集合
	 * @return
	 */
	void addRelation(Integer noticeId,List<Integer> depts,List<Integer> users,List<Integer> roles);
}
