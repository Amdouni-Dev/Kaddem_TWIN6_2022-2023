package esprit.tn.amdounidev.Aspect;//package esprit.tn.amdounidev.Aspect;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//@Slf4j
//public class aspects {}
////    // l'etoile loula optionnelle tahki aala modifiacteur d'acces
////@Around("execution(* esprit.tn.amdounidev.Services.*.*(..))")
////public void logMethodEntry(JoinPoint join){
////    String name=join.getSignature().getName();
////    log.info("In Method"+name+"..");
////}
////// aaround tekhou procedingJoinPoint ahsen wahda khater tetlanca en parallele
////}







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
        System.out.println("dans la methode : "+name+":");
    }

    @After("execution(* esprit.tn.amdounidev.Services.*.*(..))")
    public void logMethodEntry1(JoinPoint joinPoint){
        String name=joinPoint.getSignature().getName();
        System.out.println("la methode "+name+": est faite");
    }

    @Around("execution(* esprit.tn.amdounidev.Services.*.*(..))")
    public Object profile(ProceedingJoinPoint proceedingJoinPoint)throws Throwable{
        long start=System.currentTimeMillis();
        Object obj = proceedingJoinPoint.proceed();
        long elapsedTime=System.currentTimeMillis() - start;
        System.out.println("Temps d'execution de la methode  :" + elapsedTime+ "milliseconds");
        return obj;
    }
}
