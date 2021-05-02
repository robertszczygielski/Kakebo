package pl.dicedev.kakebo.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j(topic = "pl.dicedev..LogBeforeExecute")
public class LoggedUserAspect {

    @Around("@annotation(pl.dicedev.kakebo.annotations.LogBeforeExecute)")
    public Object setUser(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        log.info(joinPoint.getSignature().getName());

        return proceed;
    }
}
