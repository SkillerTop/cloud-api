package com.Gryshin.cloud_api.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    @AfterReturning(pointcut = "execution(* com.Gryshin.cloud_api.controller.FileController.*(..))", returning = "result")
    public void logMethodArguments(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        System.out.println("📌 Метод виконано: " + methodName);
        System.out.println("📌 Аргументи: " + Arrays.toString(args));
        System.out.println("📌 Повернене значення: " + result);
    }
}
