package xin.spgraceme.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.spgraceme.dao.PermissionMapper;
import xin.spgraceme.dao.RoleMapper;
import xin.spgraceme.dao.UserMapper;
import xin.spgraceme.dao.VisitMapper;
import xin.spgraceme.entity.Permission;
import xin.spgraceme.entity.Role;
import xin.spgraceme.entity.User;
import xin.spgraceme.entity.Visit;
import xin.spgraceme.service.UserService;
import xin.spgraceme.utils.StringUtil;

import java.util.List;

/**
 * @author 沈鹏
 * @date 2020/5/18 -16:07
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VisitMapper visitMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public User login(User user) {
        return userMapper.selectByUsernameAndPassword(user);
    }

    @Override
    public boolean insertUser(User user) {
        //使用shiro自带加密功能加密
//        String hashAlgorithmName = "MD5";
//        String credentials = user.getPassword();
//        Object salt = ByteSource.Util.bytes(credentials);
//        int hashIterations = 1024;
//        String result = null;
//        result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations).toString();
//        user.setPassword(result);
//        user.setSalt(user.getUsername());
//        user.setUid(StringUtil.uuid());

        //设置盐
        user.setSalt(user.getUsername());
        //设置密码
        user.setPassword(StringUtil.encodePassword(user.getPassword(),user.getSalt()));
        user.setUid(StringUtil.uuid());
        return userMapper.insertSelective(user) > 0 ;
    }

    @Override
    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public List<Role> selectRolesByUsername(String username) {
       return roleMapper.selectRolesByUsername(username);

    }

    @Override
    public List<Permission> selectPermissionByRid(String rid) {
        List<Permission> permissions = permissionMapper.selectPermissionByRid(rid);

        return permissions;
    }

    @Override
    public List<User> selectAllUser() {
        return userMapper.selectAll();
    }

    @Override
    public User selectByUid(String uid) {
        return userMapper.selectByPrimaryKey(uid);
    }

    @Override
    public Boolean updateByUid(User user) {
        return userMapper.updateByPrimaryKeySelective(user) > 0;
    }

    @Override
    public Boolean updateUserRole(String uid, String rid, Boolean checked) {
        boolean flag = false;
        if (checked){
            //添加角色
           flag =  userMapper.insertUserRole(StringUtil.uuid(),uid,rid) > 0;
        }else {
            //删除角色
            flag =  userMapper.deleteUserRole(uid,rid)>0;
        }
        return flag;
    }

    @Override
    public List<User> selectAllSales() {
        return userMapper.selectAllSales();
    }


}
