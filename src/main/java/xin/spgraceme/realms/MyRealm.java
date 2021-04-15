package xin.spgraceme.realms;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xin.spgraceme.entity.Permission;
import xin.spgraceme.entity.Role;
import xin.spgraceme.entity.User;
import xin.spgraceme.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 沈鹏
 * @date 2020/5/19 -19:44
 */
@Component
public class MyRealm extends AuthorizingRealm {



    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String username = (String) principalCollection.getPrimaryPrincipal();

        //根据用户名查询角色
        List<Role> roles = userService.selectRolesByUsername(username);
        List<Permission> permissions = new ArrayList<>();
        List<String> roles1 = new ArrayList<>();
        List<String> permission1 = new ArrayList<>();
        //根据用户名查询权限
        for (Role role : roles) {
            roles1.add(role.getRname());
            //获取权限
            permissions.addAll(userService.selectPermissionByRid(role.getRid()));
        }
        for (Permission permission : permissions) {
            permission1.add(permission.getOperation());
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roles1);
        simpleAuthorizationInfo.addStringPermissions(permission1);
        System.out.println("myReal:"+permissions);
        SecurityUtils.getSubject().getSession().setAttribute("permissions",permissions);
        SecurityUtils.getSubject().getSession().setAttribute("roles",roles);
        return simpleAuthorizationInfo;
    }


    /**
     * 设置认证匹配器
     * @param credentialsMatcher
     */
    @Autowired
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        super.setCredentialsMatcher(credentialsMatcher);
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        User user = userService.selectByUsername(username);
        SecurityUtils.getSubject().getSession().setAttribute("CUR_USER",user);
        if (user == null){
            throw  new UnknownAccountException("账号不存在");
        }
        return  new SimpleAuthenticationInfo(username,user.getPassword(), ByteSource.Util.bytes(user.getSalt()),getName());
    }
}
