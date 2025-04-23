package com.javaweb.controller;

import com.javaweb.anno.Log;
import com.javaweb.pojo.Dept;
import com.javaweb.pojo.Result;
import com.javaweb.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    //查询全部部门数据
    @GetMapping
    public Result list() {
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    //根据id删除部门（即解散部门，同时删除此部门的员工）
    @Log
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        log.info("根据id解散部门:{}", id);
        deptService.deleteById(id);
        return Result.success();
    }

    //新增部门
    @Log
    @RequestMapping
    public Result add(@RequestBody Dept dept) {
        log.info("新增部门:{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    //根据id查询部门
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据id查询部门:{}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    //修改部门信息
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("修改部门信息:{}",dept);
        deptService.update(dept);
        return Result.success();
    }

}
