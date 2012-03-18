package de.akquinet.jbosscc;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.EJBTransactionRequiredException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CMTExmapleTest {

	@EJB
	private CMTExample cmtExampleBean;

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar").addClasses(
				CMTExampleBean.class, CMTExample.class,
				TransactionLoggingInterceptor.class);
	}

	@Test(expected = EJBTransactionRequiredException.class)
	public void testCallMandatoryWithoutTransaction() throws Exception {
		cmtExampleBean.callMandatoryWithoutTransaction();
	}

	@Test
	public void testRequired() throws Exception {
		cmtExampleBean.required();
	}

	@Test(expected = EJBException.class)
	public void testCallNeverWithTransaction() throws Exception {
		cmtExampleBean.callNeverWithTransaction();
	}

}
