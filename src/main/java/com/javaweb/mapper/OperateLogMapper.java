package com.javaweb.mapper;

import com.javaweb.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperateLogMapper {

    //插入日志数据
    @Insert("insert into operate_log (operate_user, operate_user_name, operate_time, class_name, method_name, method_params, return_value, cost_time) values (#{operateUser}, #{operateUserName}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime});")
    void add(OperateLog log);

}
