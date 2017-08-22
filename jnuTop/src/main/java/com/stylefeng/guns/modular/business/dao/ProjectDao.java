package com.stylefeng.guns.modular.business.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 项目管理Dao
 *
 * @author
 * @Date 2017-08-17 13:48:19
 */
public interface ProjectDao {

    /**
     * 获取项目表
     * @author honor
     * @param condition
     * @return
     */
    List<Map<String, Object>> list(@Param("condition") String condition);
}
