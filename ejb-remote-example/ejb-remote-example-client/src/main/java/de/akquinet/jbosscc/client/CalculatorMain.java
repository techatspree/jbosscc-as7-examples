package de.akquinet.jbosscc.client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.akquinet.jbosscc.remote.Calculator;

public class CalculatorMain {

	public static void main(String[] args) throws Exception {
		CalculatorMain main = new CalculatorMain();
		Calculator calculator = main.lookup();
		int result = calculator.add(1, 1);
		System.out.println(result);

	}

	public Calculator lookup() throws NamingException {

		Properties props = new Properties();

		props.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.jboss.naming.remote.client.InitialContextFactory");
		props.put(Context.PROVIDER_URL, "remote://localhost:4447");
		props.put(Context.SECURITY_PRINCIPAL, "admin");
		props.put(Context.SECURITY_CREDENTIALS, "secret");

		final Context context = new InitialContext(props);
		final String appName = "";
		final String moduleName = "remote";
		final String distinctName = "";
		final String beanName = "calculator";
		final String viewClassName = Calculator.class.getName();

		return (Calculator) context.lookup("java:" + appName + "/" + moduleName
				+ "/" + distinctName + "/" + beanName + "!" + viewClassName);
	}
}
