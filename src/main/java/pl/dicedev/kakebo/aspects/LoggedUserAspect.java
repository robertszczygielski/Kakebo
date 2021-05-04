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

    @Around("@annotation(pl.dicedev.kakebo.annotations.LogInfo)")
    public Object logInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        log.info(joinPoint.getSignature().getName());

        return proceed;
    }

    @Around("@annotation(pl.dicedev.kakebo.annotations.LogDebug)")
    public Object logDebug(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        Object[] methodArguments = joinPoint.getArgs();
        for (Object methodArgument : methodArguments) {
            log.debug(methodArgument.toString());
        }

        return proceed;
    }
}
