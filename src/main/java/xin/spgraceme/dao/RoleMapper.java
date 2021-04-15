package xin.spgraceme.dao;

import xin.spgraceme.entity.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(String rid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String rid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectRolesByUsername(String username);

    List<Role> selectAll();

    List<Role> selectByUid(String uid);
}