package com.javaweb.service.impl;

import com.javaweb.mapper.DeptMapper;
import com.javaweb.mapper.EmpMapper;
import com.javaweb.pojo.Dept;
import com.javaweb.pojo.DeptLog;
import com.javaweb.service.DeptLogService;
import com.javaweb.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptLogService deptLogService;

    //查询全部部门数据
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    //根据id删除部门
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Integer id) {
        try {
            deptMapper.deleteById(id);
            empMapper.deleteByDeptId(id);//根据部门id删除员工
        } finally {
            //添加解散部门日志数据
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作，此次解散的是" + id + "号部门");
            deptLogService.add(deptLog);
        }
    }

    //新增部门
    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);
    }

    //根据id查询部门
    @Override
    public Dept getById(Integer id) {
        return deptMapper.selectById(id);
    }

    //修改部门信息
    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
