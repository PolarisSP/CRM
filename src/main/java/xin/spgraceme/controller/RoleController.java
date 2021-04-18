package xin.spgraceme.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.spgraceme.dto.PermissionDto;
import xin.spgraceme.dto.Result;
import xin.spgraceme.dto.RoleDto;
import xin.spgraceme.entity.Permission;
import xin.spgraceme.service.RoleService;

import java.util.List;

/**
 * @author 沈鹏
 * @date 2020/5/25 -21:13
 */
@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("getRole")
    @RequiresUser
    @RequiresRoles("系统管理员")
    @ResponseBody
    public Result<List<RoleDto>> getRole(String uid) {
        System.out.println("getRole--------------------");
        Result result = new Result();
        result.setState(Result.OK);
        List<RoleDto> role = roleService.getRole(uid);
        result.setData(role);
        return result;
    }


    @RequestMapping("getPermission")
    @RequiresUser
    @RequiresRoles("系统管理员")
    @ResponseBody
    public Result<List<PermissionDto>> getPermission(String rid) {
        Result<List<PermissionDto>> result = new Result();
        List<PermissionDto> permission = roleService.getPermission(rid);
        result.setState(Result.OK);
        result.setData(permission);
        return result;
    }

    @RequestMapping("updateRolePermission")
    @ResponseBody
    @RequiresUser
    @RequiresRoles("系统管理员")
    public Result updateRolePermission(String rid, String pid, Boolean checked) {
        Boolean isSuccess = roleService.updateRolePermission(rid, pid, checked);
        Result result = new Result<>();
        result.setState(isSuccess ? 0 : 1);
        return result;
    }
}
