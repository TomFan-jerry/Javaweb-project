package com.javaweb.aop;

import com.alibaba.fastjson.JSONObject;
import com.javaweb.mapper.OperateLogMapper;
import com.javaweb.pojo.OperateLog;
import com.javaweb.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Around("@annotation(com.javaweb.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        OperateLog operateLog = new OperateLog();

        //获取操作人id，即当前登陆的员工的id（获取请求头中的jwt令牌并解析）
        String jwt = httpServletRequest.getHeader("token");
        Claims claims = JwtUtils.parseToken(jwt);
        operateLog.setOperateUser((Integer) claims.get("id"));
        operateLog.setOperateUserName(claims.get("name").toString());

        //获取操作时间
        operateLog.setOperateTime(LocalDateTime.now());

        //获取操作类名
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());

        //获取操作方法名
        operateLog.setMethodName(joinPoint.getSignature().getName());

        //获取操作方法参数
        operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));

        long begin = System.currentTimeMillis(); //目标方法运行开始时间
        Object result = joinPoint.proceed(); //调用原始目标方法运行
        long end = System.currentTimeMillis(); //目标方法运行结束时间

        //获取操作方法返回值
        operateLog.setReturnValue(JSONObject.toJSONString(result));

        //获取操作耗时
        operateLog.setCostTime(end - begin);

        //记录操作日志
        operateLogMapper.add(operateLog);
        log.info("AOP操作日志:{}", operateLog);

        return result;

    }

}
