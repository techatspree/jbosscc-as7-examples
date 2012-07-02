package de.akquinet.jbosscc;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

@Stateless
@Interceptors(value = { TransactionLoggingInterceptor.class })
public class CMTExampleBean implements CMTExample {

	@EJB
	private CMTExample cmtExample;

	@Resource
	private SessionContext sessionContext;

	@TransactionAttribute(TransactionAttributeType.NEVER)
	public void never() {
	}

	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void mandatory() {

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void requiresNew() {
		sessionContext.setRollbackOnly();
	}

	public void required() {
		cmtExample.requiresNew();
	}

	@TransactionAttribute(TransactionAttributeType.NEVER)
	public void callMandatoryWithoutTransaction() {
		cmtExample.mandatory();
	}

	public void callNeverWithTransaction() {
		cmtExample.never();
	}

	public void callMandatory() {
		cmtExample.mandatory();
	}

}
