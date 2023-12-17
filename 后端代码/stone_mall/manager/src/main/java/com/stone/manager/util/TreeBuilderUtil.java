package com.stone.manager.util;

import com.stone.model.entity.system.SysMenu;
import com.stone.model.vo.system.SysMenuVo;
import org.springframework.boot.web.server.Cookie;

import java.util.ArrayList;
import java.util.List;

public class TreeBuilderUtil {
    public static List<SysMenu> buildMenuTree(List<SysMenu> allMenus){
        List<SysMenu> topMenus = new ArrayList<>();
        for(SysMenu menu:allMenus){
            if(menu.getParentId().longValue()==0) {
                topMenus.add(helper(menu, allMenus));
            }
        }
        return topMenus;
    }
    public static List<SysMenuVo> buildMenuVoTree(List<SysMenu> allMenus){
        List<SysMenuVo> topMenuVos = new ArrayList<>();
        for(SysMenu menu:allMenus){
            if(menu.getParentId().longValue()==0) {
                topMenuVos.add(helper2(menu,allMenus));
            }
        }
        return topMenuVos;
    }

    private static SysMenu helper(SysMenu menu, List<SysMenu> allMenus){
        List<SysMenu> list = new ArrayList<>();
        for(SysMenu cur: allMenus){
            if(cur.getParentId().longValue()==menu.getId().longValue()) {
                list.add(helper(cur,allMenus));
            }
        }
        menu.setChildren(list);
        return menu;
    }
    private static SysMenuVo helper2(SysMenu menu, List<SysMenu> allMenus){
        SysMenuVo sysMenuVo = new SysMenuVo();
        sysMenuVo.setName(menu.getComponent());
        sysMenuVo.setTitle(menu.getTitle());
        List<SysMenuVo> list = new ArrayList<>();
        for(SysMenu cur: allMenus){
            if(cur.getParentId().longValue()==menu.getId().longValue()) {
                list.add(helper2(cur,allMenus));
            }
        }
        if(list.size()>0)
            sysMenuVo.setChildren(list);
        return sysMenuVo;
    }

}
