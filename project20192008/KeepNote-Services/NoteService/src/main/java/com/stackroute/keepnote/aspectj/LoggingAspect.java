package com.stackroute.keepnote.aspectj;

import java.util.Date;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/* Annotate this class with @Aspect and @Component */
@Aspect
@Component
public class LoggingAspect {
	/*
	 * Write loggers for each of the methods of Note controller, any particular
	 * method will have all the four aspectJ annotation
	 * (@Before, @After, @AfterReturning, @AfterThrowing).
	 */
	/*
	@Before("controller() && allMethod()")
	public void logBefore() {
		System.out.println("Aspect @Before : " + new Date());
	}

	@After("controller() && allMethod()")
	public void logAfter() {
		System.out.println("Aspect @After : " + new Date());
	}

	@AfterReturning(pointcut = "controller() && allMethod()", returning = "val")
	public void logAfterReturning(Object val) {
		System.out.println("Return value : " + val);
		System.out.println("Aspect @AfterReturning : " + new Date());
	}

	@AfterThrowing(pointcut = "controller() && allMethod()", throwing = "exception")
	public void logAfterThrowing(Exception exception) {
		System.out.println("Exception :" + exception.getMessage());
		System.out.println("Aspect @AfterThrowing:" + new Date());
	}*/
}
