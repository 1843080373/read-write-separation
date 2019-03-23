package com.rd.db.conf;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAop {

    @Pointcut("!@annotation(com.rd.db.conf.Master) " +
            "&& (execution(* com.rd.db.service..*.select*(..)) " +
            "|| execution(* com.rd.db.service..*.get*(..)))")
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.rd.db.conf.Master) " +
            "|| execution(* com.rd.db.service..*.insert*(..)) " +
            "|| execution(* com.rd.db.service..*.add*(..)) " +
            "|| execution(* com.rd.db.service..*.update*(..)) " +
            "|| execution(* com.rd.db.service..*.edit*(..)) " +
            "|| execution(* com.rd.db.service..*.delete*(..)) " +
            "|| execution(* com.rd.db.service..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }
}