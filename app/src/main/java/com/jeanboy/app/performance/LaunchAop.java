package com.jeanboy.app.performance;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by jeanboy on 2021/4/11 14:12.
 */

//@Aspect
public class LaunchAop {

//    @Around("call(* com.jeanboy.app.performance.MainActivity.**(..))")
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


//    @Around("execution(* android.app.Activity.setContentView(..))")
    public void getContentViewTime(ProceedingJoinPoint joinPoint) {
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
