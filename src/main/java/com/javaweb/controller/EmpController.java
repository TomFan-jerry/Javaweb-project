package com.javaweb.controller;

import com.javaweb.pojo.Emp;
import com.javaweb.pojo.PageBean;
import com.javaweb.pojo.Result;
import com.javaweb.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    //分页查询员工数据
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer pageSize, String name, Short gender, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询员工数据,参数:{},{},{},{},{},{}", page, pageSize, name, gender, begin, end);
        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    //删除员工数据
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("根据id删除员工数据:{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    //添加员工数据
    @PostMapping
    public Result add(@RequestBody Emp emp) {
        log.info("添加员工:{}", emp);
        empService.add(emp);
        return Result.success();
    }

    //根据id查询员工数据
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据id查询员工:{}", id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    //修改员工数据
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工数据:{}", emp);
        empService.update(emp);
        return Result.success();
    }

}
