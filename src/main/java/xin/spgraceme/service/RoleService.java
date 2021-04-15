package xin.spgraceme.service;

import xin.spgraceme.dto.PermissionDto;
import xin.spgraceme.dto.Result;
import xin.spgraceme.dto.RoleDto;

import java.util.List;

/**
 * @author 沈鹏
 * @date 2020/5/25 -21:19
 */
public interface RoleService {

    List<RoleDto> getRole(String uid);

    List<PermissionDto> getPermission(String rid);

    Boolean updateRolePermission(String rid, String pid, Boolean checked);
}
