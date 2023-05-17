package com.lmyxlf.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmyxlf.annotation.RedisAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;


/**
 * 使用 aop 实现 redis 手动序列化
 *
 * @author lmy
 */
@Aspect
@Component
public class RedisAspect {
    @Autowired
    private StringRedisTemplate redisTemplate;
    /**
     * JSON工具
     */
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * 切入点
     */
    @Pointcut("execution(* com.lmyxlf.service..impl..*(..))")
    public void pointCut(){}

    /**
     * 环绕通知
     *
     * @param pjp 切入的目标
     * @return 目标方法执行后返回值
     * @throws Throwable 目标方法可能抛出的异常
     */
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 获得 RedisAnnotation 注解，如果没有则表示不需要缓存
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        RedisAnnotation annotations = method.getAnnotation(RedisAnnotation.class);
        if(annotations==null){
            // 没有 RedisAnnotation 注解，不需要缓存
            return pjp.proceed();
        }

        // 此时能下来的就说明需要缓存
        StringBuilder keyName= new StringBuilder();
        if(pjp.getArgs().length>0 && annotations.value().length>0){
            // 假设 i<annotations.keyName().length==pjp.getArgs().length，传参严格遵守
            for(int i=0;i<annotations.value().length;i++){
                keyName.append(annotations.value()[i]).append(pjp.getArgs()[i]);
            }
        }else {
            keyName = new StringBuilder(annotations.value()[0]);
        }
        // 查缓存
        String cache = redisTemplate.opsForValue().get(keyName.toString());
        if(cache!=null){
            return mapper.readValue(cache, Object.class);
        }

        // 执行方法
        Object result = pjp.proceed();

        // 添加 redis 缓存
        // 手动序列化，解决 no default no-arguments constructor found
        String json = mapper.writeValueAsString(result);
        redisTemplate.opsForValue().set(keyName.toString(),json);
        return result;
    }
}