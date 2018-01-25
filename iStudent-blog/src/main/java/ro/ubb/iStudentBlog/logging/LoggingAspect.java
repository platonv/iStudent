package ro.ubb.iStudentBlog.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ro.ubb.iStudentBlog.logging.adjuster.LogAdjusterChain;
import ro.ubb.iStudentBlog.logging.enumeration.LogMomentEnum;

import static ro.ubb.iStudentBlog.logging.enumeration.LogMomentEnum.AFTER;
import static ro.ubb.iStudentBlog.logging.enumeration.LogMomentEnum.BEFORE;

/**
 * @author ciprian.mosincat on 12/30/2017.
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {

    private final Level loggingLevel;
    private final LogAdjusterChain logAdjusterChain;

    public LoggingAspect(@Value("${logging.level.ro.ubb.iStudentBlog}") final Level loggingLevel,
                         final LogAdjusterChain logAdjusterChain) {
        this.logAdjusterChain = logAdjusterChain;
        this.loggingLevel = loggingLevel;
    }

    @Pointcut("execution(@LogMethodCall * *.*(..))")
    void annotatedMethod() {
    }

    @Pointcut("execution(* (@LogMethodCall *).*(..))")
    void methodOfAnnotatedClass() {
    }

    @Pointcut(value = "@within(logMethodCall) && !annotatedMethod() && methodOfAnnotatedClass()",
            argNames = "logMethodCall")
    public void logMethodCallFromClass(final LogMethodCall logMethodCall) {
    }

    @Pointcut(value = "@annotation(logMethodCall) && annotatedMethod()",
            argNames = "logMethodCall")
    public void logMethodCall(final LogMethodCall logMethodCall) {
    }

    @Around(value = "logMethodCallFromClass(annotation)", argNames = "joinPoint,annotation")
    public Object aroundMethodFromClassCall(final ProceedingJoinPoint joinPoint,
                                            final LogMethodCall annotation) throws Throwable {
        return logMethodCall(joinPoint, annotation);
    }

    @Around(value = "logMethodCall(annotation)", argNames = "joinPoint,annotation")
    public Object aroundMethodCall(final ProceedingJoinPoint joinPoint,
                                   final LogMethodCall annotation) throws Throwable {
        return logMethodCall(joinPoint, annotation);
    }

    private Object logMethodCall(final ProceedingJoinPoint joinPoint,
                                 final LogMethodCall logMethodCall) throws Throwable {
        final Level logMethodLevel = logMethodCall.level();
        if (!checkLoggingLevel(logMethodLevel)) {
            return joinPoint.proceed();
        }

        this.logAdjusterChain.doChainAdjust(createLogEvent(BEFORE, joinPoint, logMethodLevel));

        final Object returnObject = joinPoint.proceed();

        this.logAdjusterChain.doChainAdjust(createLogEvent(AFTER, joinPoint, logMethodLevel));
        return returnObject;
    }

    private LogEvent createLogEvent(final LogMomentEnum logMomentEnum,
                                    final ProceedingJoinPoint joinPoint,
                                    final Level logMethodLevel) {
        if (BEFORE.equals(logMomentEnum)) {
            final String message = "Before execution of " + joinPoint.getSignature().getName() + " method";
            return new LogEvent(logMethodLevel, message);
        } else {
            final String afterMessage = "Method " + joinPoint.getSignature().getName() + " was executed";
            return new LogEvent(logMethodLevel, afterMessage);
        }
    }

    private boolean checkLoggingLevel(final Level level) {
        return level.toInt() >= loggingLevel.toInt();
    }

}
