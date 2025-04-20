package com.javaweb.service;

import com.javaweb.pojo.Dept;

import java.util.List;

public interface DeptService {

    //查询全部部门数据
    List<Dept> list();

    //根据id删除部门
    void deleteById(Integer id);

    //新增部门
    void add(Dept dept);

    //根据id查询部门
    Dept getById(Integer id);

    //修改部门信息
    void update(Dept dept);
}
