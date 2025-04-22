package com.javaweb.service.impl;

import com.javaweb.mapper.DeptLogMapper;
import com.javaweb.pojo.DeptLog;
import com.javaweb.service.DeptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeptLogServiceImpl implements DeptLogService {

    @Autowired
    private DeptLogMapper deptLogMapper;

    //添加解散部门日志数据
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void add(DeptLog deptLog) {
        deptLogMapper.add(deptLog);
    }
}
