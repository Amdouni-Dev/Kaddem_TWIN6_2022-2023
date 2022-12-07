/*package esprit.tn.amdounidev.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Aspects {

    @Before("execution(* esprit.tn.amdounidev.Services.*.*(..))")
    public void logMethodEntry(JoinPoint joinPoint){
        String name=joinPoint.getSignature().getName();
        System.out.println("In method"+name+":");
    }

    @After("execution(* esprit.tn.amdounidev.Services.*.*(..))")
    public void logMethodEntry1(JoinPoint joinPoint){
        String name=joinPoint.getSignature().getName();
        System.out.println("In method"+name+":");
    }

    @Around("execution(* esprit.tn.amdounidev.Services.*.*(..))")
    public Object profile(ProceedingJoinPoint proceedingJoinPoint)throws Throwable{
        long start=System.currentTimeMillis();
        Object obj = proceedingJoinPoint.proceed();
        long elapsedTime=System.currentTimeMillis() - start;
        System.out.println("Method execution time :" + elapsedTime+ "milliseconds");
        return obj;
    }
}
*/
