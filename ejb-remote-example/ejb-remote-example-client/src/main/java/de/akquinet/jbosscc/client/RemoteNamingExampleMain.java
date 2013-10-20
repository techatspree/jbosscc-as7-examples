package de.akquinet.jbosscc.client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.akquinet.jbosscc.remote.Calculator;

public class RemoteNamingExampleMain {
	public static void main(String[] args) throws Exception {
		RemoteNamingExampleMain main = new RemoteNamingExampleMain();
		Calculator calculator = main.lookup();
		System.out.println(calculator);
		
		int result = calculator.add(1, 1);
		System.out.println(result);

	}

	public Calculator lookup() throws NamingException {

		Properties prop = new Properties();

		prop.put("jboss.naming.client.ejb.context", true);


		prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");

		prop.put(Context.PROVIDER_URL, "remote://127.0.0.1:4447");
		prop.put(Context.SECURITY_PRINCIPAL, "admin");
		prop.put(Context.SECURITY_CREDENTIALS, "secret123!");

		final Context context = new InitialContext(prop);
		
		
		final String appName = "";
		final String moduleName = "remote";
		final String distinctName = "";
		final String beanName = "calculator";
		final String viewClassName = Calculator.class.getName();

		return (Calculator) context.lookup("" + appName + "/" + moduleName
				+ "/" + distinctName + "/" + beanName + "!" + viewClassName);
	}
}
