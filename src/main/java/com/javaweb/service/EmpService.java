package com.javaweb.service;

import com.javaweb.pojo.Emp;
import com.javaweb.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

    //分页查询员工数据
    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    //根据id删除员工数据
    void delete(List<Integer> ids);

    //添加员工数据
    void add(Emp emp);

    //根据id查询员工数据
    Emp getById(Integer id);

    //修改员工数据
    void update(Emp emp);

    //员工登录
    Emp login(Emp emp);
}
