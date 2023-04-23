package refresh.ManageSystem.util.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
/**
 * Daniel kim
 *
 * LogAspect : Spring AOP 를 이용해 controller 와 Service 의 log 를 종단으로 관리할 클래스
 *
 * 2023-04-15
 */
@Aspect // 애스펙트 등록
@Slf4j // 롬복 log Annotation
@Component
public class LogAspect {

    /**
     * Daniel Kim
     *
     * @param pjp
     * @return
     * @throws Throwable
     * proceed 즉 모든 Controller 가 실행 된 후 log 를 찍어주는 Advice
     *
     * 2023-04-15
     */
    @Around("execution(* tko..controller.*Controller.*(..))")
    public Object loginLogging(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;
        try {
            result = pjp.proceed();
            log.debug("debug log={}", getClass());
            log.error("error log={}", getClass());

        } catch (Throwable t) {
            System.out.println("Controller Exception");
        }
        return result;
    }

    /**
     * Daniel Kim
     *
     * @param pjp
     * @return
     * @throws Throwable
     * proceed 즉 모든 Service 가 실행 된 후 log 를 찍어주는 Advice
     *
     * 2023-04-15
     */
    @Around("execution(* tko..service.*Service.*(..))")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;
        try {
            result = pjp.proceed();
            log.debug("debug log={}", getClass());
            log.error("error log={}", getClass());
        } catch (Throwable throwable) {
            System.out.println("Service Exception");
        }
        return result;
    }
}
