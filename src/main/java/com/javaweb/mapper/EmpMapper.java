package com.javaweb.mapper;

import com.github.pagehelper.Page;
import com.javaweb.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    //分页查询员工数据
    Page<Emp> pageList(String name, Short gender, LocalDate begin, LocalDate end);

    //根据id删除员工数据
    void delete(List<Integer> ids);

    //添加员工数据
    @Insert("insert into emp(username, name, gender, image, job, entry_date, dept_id, create_time, update_time) values (#{username}, #{name}, #{gender}, #{image}, #{job}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void add(Emp emp);

    //根据id查询员工数据
    @Select("select * from emp where id = #{id}")
    Emp getById(Integer id);

    //修改员工数据
    @Update("update emp set image = #{image}, username = #{username}, name = #{name}, gender = #{gender}, job = #{job}, entry_date = #{entryDate}, dept_id = #{deptId}, update_time = #{updateTime} where id = #{id}")
    void update(Emp emp);
}
