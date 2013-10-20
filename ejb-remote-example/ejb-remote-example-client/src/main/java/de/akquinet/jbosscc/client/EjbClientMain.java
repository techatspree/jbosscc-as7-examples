package de.akquinet.jbosscc.client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.akquinet.jbosscc.remote.Calculator;

public class EjbClientMain {

	public static void main(String[] args) throws Exception {
		EjbClientMain main = new EjbClientMain();
		Calculator calculator = main.lookup();
		int result = calculator.add(1, 1);
		System.out.println(result);

	}

	public Calculator lookup() throws NamingException {

		Properties prop = new Properties();
		prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		final Context context = new InitialContext(prop);
		final String appName = "";
		final String moduleName = "remote";
		final String distinctName = "";
		final String beanName = "calculator";
		final String viewClassName = Calculator.class.getName();
		
		return (Calculator) context.lookup("ejb:" + appName + "/" + moduleName
				+ "/" + distinctName + "/" + beanName + "!" + viewClassName);
	}
}
