package esprit.tn.amdounidev.AOPEuipe;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class EquipeAspect {

    @Before("execution(* esprit.tn.amdounidev.Services.EquipeServiceImpl.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("Maintenant dans la methode de  " + name + "  ");
    }
    @After("execution(* esprit.tn.amdounidev.Services.ExcelService.*(..))")
    public void logMethodEntry2(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("le telechargement est faite a travers la methode :  " + name + "  ");
    }
}
