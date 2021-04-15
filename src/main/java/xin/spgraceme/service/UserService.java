package xin.spgraceme.service;

import xin.spgraceme.entity.Permission;
import xin.spgraceme.entity.Role;
import xin.spgraceme.entity.User;
import xin.spgraceme.entity.Visit;

import java.util.List;

/**
 * @author 沈鹏
 * @date 2020/5/18 -16:07
 */
public interface UserService {

    User login(User user);

    boolean insertUser(User user);

    User selectByUsername(String username);

    List<Role> selectRolesByUsername(String username);

    List<Permission> selectPermissionByRid(String rid);

    List<User> selectAllUser();

    User selectByUid(String uid);

    Boolean updateByUid(User user);

    Boolean updateUserRole(String uid, String rid, Boolean checked);


    List<User> selectAllSales();

}
