package com.javaweb.mapper;

import com.javaweb.pojo.DeptLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptLogMapper {

    //添加解散部门日志数据
    @Insert("insert into dept_log(create_time, description) values (#{createTime}, #{description})")
    void add(DeptLog deptLog);
}
