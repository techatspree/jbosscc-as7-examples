package de.akquinet.jbosscc;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CalculatorTest {

	@EJB
	private Calculator calculator;

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar").addClass(
				Calculator.class);
	}

	@Test
	public void testCalculator() throws Exception {
		Assert.assertNotNull(calculator);
	}

}
