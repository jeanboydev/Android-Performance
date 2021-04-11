package com.jeanboy.component.performance;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by jeanboy on 2021/4/11 14:12.
 */

@Aspect
public class LaunchAop {

    @Around("call(* com.astrology.component.core.AppManager.**(..))")
    public void getTime(ProceedingJoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();

        Signature signature = joinPoint.getSignature();
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        Log.e("Performance", signature.toShortString() + " cost: " + (endTime - startTime) + "ms");
    }
}
