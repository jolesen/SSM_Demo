package com.stylefeng.guns.modular.system.dao;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author zmj
 * @since 2017年8月26日上午11:28:34
 *
 */
public interface NoticeUserRelationDao {
	
	/**
	 * 删除通知用户关系
	 * @param NoticeId 通知Id
	 * @author zmj
	 * @since 2017年8月26日上午11:31:32
	 */
	void deleteRelationByNoticeId(@Param("noticeId") Integer NoticeId);
	
	/**
	 * 删除通知用户关系
	 * @param UserId 用户Id
	 * @param NoticeId 通知Id
	 * @author zmj
	 * @since 2017年8月26日上午11:31:56
	 */
	void deleteRelationByUserId(@Param("userId") Integer UserId,@Param("noticeId") Integer NoticeId);
}
