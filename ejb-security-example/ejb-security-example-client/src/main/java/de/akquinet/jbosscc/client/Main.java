package de.akquinet.jbosscc.client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.akquinet.jbosscc.remote.Calculator;

public class Main {

	public static void main(String[] args) throws Exception {
		Main main = new Main();
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
		props.put("jboss.naming.client.ejb.context", true);
		props.put(Context.SECURITY_CREDENTIALS, "secret");

		props.put(Context.INITIAL_CONTEXT_FACTORY,
				org.jboss.naming.remote.client.InitialContextFactory.class
						.getName());

		props.put(
				"jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT",
				"false");

		final Context context = new InitialContext(props);
		final String appName = "";
		final String moduleName = "calculator";
		final String distinctName = "";
		final String beanName = "calculator";
		final String viewClassName = Calculator.class.getName();

		return (Calculator) context.lookup("java:" + appName + "/" + moduleName
				+ "/" + distinctName + "/" + beanName + "!" + viewClassName);
	}
}
