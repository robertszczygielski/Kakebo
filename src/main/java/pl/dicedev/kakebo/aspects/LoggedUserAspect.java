package pl.dicedev.kakebo.aspects;

import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import pl.dicedev.kakebo.services.impl.UserLogInfoService;

@Aspect
@Component
@AllArgsConstructor
public class LoggedUserAspect {

    private final UserLogInfoService userLogInfoService;

    @Around("@annotation(pl.dicedev.kakebo.annotations.LoggedUser)")
    public Object setUser(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("in aspect");
        return joinPoint.proceed();
    }
}
