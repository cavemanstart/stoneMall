package com.stone.utils;

import com.stone.model.entity.system.SysUser;
import com.stone.model.entity.user.UserInfo;

public class AuthContextUtil {
    private static final ThreadLocal<SysUser> THREAD_LOCAL = new ThreadLocal<>();

    private static final ThreadLocal<UserInfo> userInfoThreadLocal = new ThreadLocal<>() ;
    // 定义存储数据的静态方法

    public static void set(SysUser sysUser){
        THREAD_LOCAL.set(sysUser);
    }
    public static SysUser get(){
        return THREAD_LOCAL.get();
    }
    public static void remove(){
        THREAD_LOCAL.remove();
    }

    public static void setUserInfo(UserInfo userInfo) {
        userInfoThreadLocal.set(userInfo);
    }

    // 定义获取数据的方法
    public static UserInfo getUserInfo() {
        return userInfoThreadLocal.get() ;
    }

    // 删除数据的方法
    public static void removeUserInfo() {
        userInfoThreadLocal.remove();
    }
}
