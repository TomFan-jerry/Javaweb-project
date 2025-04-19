package com.javaweb.service.impl;

import com.javaweb.mapper.DeptMapper;
import com.javaweb.pojo.Dept;
import com.javaweb.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    //查询全部部门数据
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    //根据id删除部门
    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
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
