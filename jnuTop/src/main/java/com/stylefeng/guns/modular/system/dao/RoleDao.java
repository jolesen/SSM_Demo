package com.stylefeng.guns.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.stylefeng.guns.common.node.ZTreeNode;
import com.stylefeng.guns.common.persistence.model.Role;

/**
 * 角色相关的dao
 *
 * @author fengshuonan
 * @date 2017年2月12日 下午8:43:52
 */
public interface RoleDao {

    /**
     * 根据条件查询角色列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    List<Map<String, Object>> selectRoles(@Param("condition") String condition);

    /**
     * 删除某个角色的所有权限
     *
     * @param roleId 角色id
     * @return
     * @date 2017年2月13日 下午7:57:51
     */
    int deleteRolesById(@Param("roleId") Integer roleId);

    /**
     * 获取角色列表树
     *
     * @return
     * @date 2017年2月18日 上午10:32:04
     */
    List<ZTreeNode> roleTreeList();

    /**
     * 获取角色列表树
     *
     * @return
     * @date 2017年2月18日 上午10:32:04
     */
    List<ZTreeNode> roleTreeListByRoleId(String[] roleId);
    
    
    /**
     * 插入角色
     * @param role
     * @return
     */
    Integer insertRole(Role role);
    
    
    /**
     * 通过 部门id获取角色
     * @param deptId 部门id
     * @return 角色
     */
    Role selectRoleByDepId(Integer deptId);
    
    /**
     * 通过角色id获取所有权限id
     * @param orderId
     * @return
     */
    List<Integer> selectMenuIdsByRoleId(@Param("roleId") Integer roleId);
    
    /**
     * 通过角色id查找角色
     * @param roleId
     * @return
     */
    Role selectById(Integer roleId);
    
    /**
     * 通过角色名查找角色
     * @param roleName
     * @return
     */
    Role selectByRoleName(@Param("roleName")String roleName);



}
