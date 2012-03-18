package de.akquinet.jbosscc;

import javax.annotation.Resource;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.transaction.TransactionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TransactionLoggingInterceptor {
	private static final Logger LOG = LoggerFactory
			.getLogger(TransactionLoggingInterceptor.class);

	@Resource(mappedName = "java:/TransactionManager")
	private TransactionManager transactionManager;

	@AroundInvoke
	public Object invoke(InvocationContext ctx) throws Exception {
		String methodName = ctx.getMethod().getName();

		LOG.info(methodName + " is being called with transaction {}", transactionManager.getTransaction());
		return ctx.proceed();
	}
}
