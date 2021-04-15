package xin.spgraceme.dao;

import org.apache.ibatis.annotations.Param;
import xin.spgraceme.entity.Permission;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(String pid);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> selectPermissionByRid(String rid);

    List<Permission> selectAll();

    List<Permission> selectByRid(String rid);

    List<Permission> selectByParentId(String pid);

    void insertRolePermission(@Param("rpid") String rpid, @Param("rid")String rid, @Param("pid")String pid);

    Permission selectByRidPid(@Param("rid")String rid, @Param("pid")String parentId);

    void deleteByRolePermission(@Param("rid")String rid, @Param("pid")String pid);

    List<Permission> selectByRidParentid(@Param("rid")String rid, @Param("pid")String parentId);
}