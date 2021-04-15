package xin.spgraceme.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.spgraceme.dao.PermissionMapper;
import xin.spgraceme.dao.RoleMapper;
import xin.spgraceme.dto.PermissionDto;
import xin.spgraceme.dto.RoleDto;
import xin.spgraceme.entity.Permission;
import xin.spgraceme.entity.Role;
import xin.spgraceme.service.RoleService;
import xin.spgraceme.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 沈鹏
 * @date 2020/5/25 -21:20
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public List<RoleDto> getRole(String uid) {
        //先查询所有的角色
        List<Role> roles1 = roleMapper.selectAll();
        //再查询该用户有的角色
        List<Role> roles2 = roleMapper.selectByUid(uid);

        List<RoleDto> list = new ArrayList<>();
        for (Role role : roles1) {
            RoleDto roleDto = new RoleDto();
            roleDto.setId(role.getRid());
            roleDto.setTitle(role.getRname());
            for (Role role1 : roles2) {
                if (role.getRid().equals(role1.getRid())){
                    roleDto.setChecked(true);
                    break;
                }
            }
            list.add(roleDto);
        }
        return list;
    }

    @Override
    public List<PermissionDto> getPermission(String rid) {
        //查询所有权限
        List<Permission> permissions1 = permissionMapper.selectAll();
        //查询该角色所有的权限
        List<Permission> permissions2 = permissionMapper.selectByRid(rid);
        //要返回给页面的DTO
        List<PermissionDto> permissionDtoList = new ArrayList<>();

        //查询一级菜单
        for (Permission permission : permissions1) {
            if(permission.getLevel()==1){
                PermissionDto permissionDto = new PermissionDto();
                permissionDto.setTitle(permission.getPname());
                permissionDto.setId(permission.getPid());
                permissionDto.setSpread(true);
                permissionDto.setField("");
                permissionDto.setChildren(new ArrayList<PermissionDto>());
                for (Permission permission1 : permissions2) {
                    if (permission.getPid().equals(permission1.getPid())){
                        permissionDto.setChecked(true);
                        break;
                    }
                }
                permissionDtoList.add(permissionDto);
            }

        }

        //查询二级菜单
        for (Permission permission : permissions1) {
            if(permission.getLevel()==2){
                PermissionDto permissionDto = new PermissionDto();
                permissionDto.setTitle(permission.getPname());
                permissionDto.setId(permission.getPid());
                permissionDto.setSpread(true);
                permissionDto.setField("");
                for (Permission permission1 : permissions2) {
                    if (permission.getPid().equals(permission1.getPid())){
                        permissionDto.setChecked(true);
                        break;
                    }
                }

                for (PermissionDto dto : permissionDtoList) {
                        if(dto.getId().equals(permission.getParentId())){
                            dto.getChildren().add(permissionDto);
                        }
                }
            }

        }

        return permissionDtoList;
    }

    @Override
    public Boolean updateRolePermission(String rid, String pid, Boolean checked) {
        //通过pid 查找权限
        Permission permission = permissionMapper.selectByPrimaryKey(pid);
        if(checked){//如果是选中
            if(permission.getLevel() == 1){// 1级权限
                //需要把父级和子级都添加进去
                //找到子级
                List<Permission> permissions = permissionMapper.selectByParentId(pid);
                //添加子级
                for(Permission permission1 : permissions){
                    if(permissionMapper.selectByRidPid(rid,permission1.getPid()) == null)
                        permissionMapper.insertRolePermission(StringUtil.uuid(),rid,permission1.getPid());
                }

            }else if(permission.getLevel() == 2){
                //需要判断父级有没有选中
                //如果父级没有选中，那么需要添加父级节点
                Permission permission1 = permissionMapper.selectByRidPid(rid,permission.getParentId());
                if(permission1 == null){
                    //父级没有添加，需要添加一个进去

                    permissionMapper.insertRolePermission(StringUtil.uuid(),rid,permission.getParentId());
                }
            }
            //添加当前节点
            if(permissionMapper.selectByRidPid(rid,pid) == null)
                permissionMapper.insertRolePermission(StringUtil.uuid(),rid,pid);
        } else{
            //删除当前层级
            permissionMapper.deleteByRolePermission(rid,pid);
            //取消选中
            if(permission.getLevel() == 1){// 1级权限
                //需要把父级和子级都取消
                List<Permission> permissions = permissionMapper.selectByParentId(pid);
                for(Permission permission1 : permissions){
                    permissionMapper.deleteByRolePermission(rid,permission1.getPid());
                }
            }else if(permission.getLevel() == 2){
                //需要判断父级有没有其他子级被选中
                //如果没有就删掉父级
                List<Permission> permission1 = permissionMapper.selectByRidParentid(rid,permission.getParentId());
                if(permission1 == null || permission1.isEmpty()){
                    permissionMapper.deleteByRolePermission(rid,permission.getParentId());
                }
            }
        }
        return true;
    }
}
