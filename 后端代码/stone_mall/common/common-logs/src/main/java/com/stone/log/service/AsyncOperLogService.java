package com.stone.log.service;

import com.stone.model.entity.system.SysOperLog;

public interface AsyncOperLogService {//保存日志数据
    public abstract void saveSysOperLog(SysOperLog sysOperLog) ;
}
