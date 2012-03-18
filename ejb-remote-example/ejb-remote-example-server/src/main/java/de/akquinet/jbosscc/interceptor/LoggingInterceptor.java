package de.akquinet.jbosscc.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingInterceptor {
	private static final Logger log = LoggerFactory
			.getLogger(LoggingInterceptor.class);

	@AroundInvoke
	public Object invoke(InvocationContext ctx) throws Exception {
		String methodName = ctx.getMethod().getName();
		log.info(methodName + " is being called");
		return ctx.proceed();
	}
}
