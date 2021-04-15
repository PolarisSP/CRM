package xin.spgraceme.utils;

import xin.spgraceme.dto.InitMenu;
import xin.spgraceme.dto.Menu;
import xin.spgraceme.entity.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 沈鹏
 * @date 2020/5/21 -19:28
 */
public class PermissionConvert {


    /**
     * 将Realm中的权限装换为json，供前台页面显示
     * @param permissions
     * @return
     */
    public static InitMenu convert(List<Permission> permissions){
        InitMenu initMenu = new InitMenu();
        Menu homeInfo = new Menu();
        initMenu.setHomeInfo(homeInfo);
        homeInfo.setTitle("首页");
        homeInfo.setHref("page/welcome-1.html");
        Menu logoInfo = new Menu();
        initMenu.setLogoInfo(logoInfo);
        logoInfo.setTitle("CRM管理");
        logoInfo.setImage("layuimini/images/logo.png");

        List<Menu> menuInfo = new ArrayList<>();
        initMenu.setMenuInfo(menuInfo);
        Menu geneal = new Menu();
        menuInfo.add(geneal);
        geneal.setTitle("常规管理");
        geneal.setTarget("_self");
        geneal.setIcon("fa fa-address-book");
        List<Menu> Child = new ArrayList<>();
        geneal.setChild(Child);

        if (permissions!= null){
            for (Permission permission : permissions) {
                if (permission.getLevel()==1){

                    Menu menu = new Menu();
                    menu.setTitle(permission.getPname());
                    menu.setIcon("fa fa-tachometer");
                    menu.setTarget("_self");
                    menu.setChild(getChild(permission.getPid(),permissions));
                    Child.add(menu);
                }
            }
        }
        return initMenu;
    }


    /**
     * 获取子菜单
     * @param pid
     * @param permissions
     * @return
     */
    public static List<Menu> getChild(String pid,List<Permission> permissions){
        List<Menu> childs = new ArrayList<>();
        for(Permission permission : permissions){
            if(permission.getLevel() == 2 && pid.equals(permission.getParentId())){
                Menu menu = new Menu();
                menu.setTitle(permission.getPname());//菜单的名字
                menu.setIcon("fa fa-address-book");
                menu.setHref(permission.getUrl());
                menu.setTarget("_self");
                childs.add(menu);
            }
        }
        return childs;
    }
}
