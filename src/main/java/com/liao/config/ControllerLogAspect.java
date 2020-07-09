package com.liao.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;



/**
 *
 * TODO: Controller日志打印 切面类
 * @author LiAo
 * @date 2020/5/20 16:18
 */
@Aspect     // 声明该类是一个注解类
@Component
public class ControllerLogAspect {

    private final static Logger logger = (Logger) LoggerFactory.getLogger(ControllerLogAspect.class);

    // 换行符
    private static final String LINE_SEPARATOR = System.lineSeparator();

    // 以 controller 包下定义的所有请求为切入点
    @Pointcut("@annotation(com.liao.config.ControllerLog)")

    public void controllerLog() {
    }

    /**
     * 在切点之前织入
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("controllerLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 获取注解描述信息
        String methodDescription = getAspectLogDescription(joinPoint);

        // 打印请求相关参数
        logger.info("========================================== Start ==========================================");

        // 打印请求url
        logger.info("URL                : {}" , request.getRequestURI().toString());

        // 打印描述信息
        logger.info("Description        : {}" , methodDescription);

        // 打印 Http method
        logger.info("HTTP Method        : {}" , request.getMethod());

        // 打印Controller 全路径
        logger.info("Controller Path    : {}" , joinPoint.getSignature().getDeclaringTypeName());

        // 打印Controller 执行方法
        logger.info("Execution Method   : {}" , joinPoint.getSignature().getName());

        //打印请求IP
        logger.info("IP                 : {}" , request.getRemoteAddr());

        // 打印请求入参
        /*logger.info("Request Args       : {}" + new Gson().toJson(joinPoint.getArgs()));*/
        logger.info("Request Args       : {}" , Arrays.toString(joinPoint.getArgs()));

    }


    /**
     * 在切点之后织入
     *
     * @throws Throwable
     */
    @After("controllerLog()")
    public void doAfter() throws Throwable {
        logger.info("=========================================== End ==========================================="+LINE_SEPARATOR);
    }

    /**
     * 环绕
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("controllerLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long strtTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();

        // 打印出参
//        logger.info("Response Args      :{}" , new Gson().toJson(result));
        logger.info("Response Args      :{}" , result.toString());

        // 执行耗时
        logger.info("Time-Consuming     : {} ms" , (System.currentTimeMillis() - strtTime));

        return result;
    }

    public String getAspectLogDescription(JoinPoint joinPoint) throws Exception {
        String targeName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] argumets = joinPoint.getArgs();
        Class targetClass = Class.forName(targeName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] classes = method.getParameterTypes();
                if (classes.length == argumets.length) {
                    description.append(method.getAnnotation(ControllerLog.class).description());
                    break;
                }
            }
        }

        return description.toString();
    }
}
