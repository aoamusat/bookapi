package io.hexcore.bookapi.util;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Aspect
@RestControllerAdvice
public class RequestLogger {
    private static final Logger LOGGER = LogManager.getLogger(RequestLogger.class);

    @Around("execution(* io.hexcore.bookapi.controller.*.*(..))")
    public Object logRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        // Get request details
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();

        // Log before method execution
        LOGGER.info("Executing {}.{} with arguments: {}", className, methodName, Arrays.toString(args));

        long startTime = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();

            // Log after successful execution
            long duration = System.currentTimeMillis() - startTime;
            LOGGER.info("{}.{} completed in {}ms with result: {}", className, methodName, duration, result);

            return result;
        } catch (Exception e) {
            // Log if there's an error
            LOGGER.error("{}.{} failed with error: {}", className, methodName, e.getMessage());
            throw e;
        }
    }
}
