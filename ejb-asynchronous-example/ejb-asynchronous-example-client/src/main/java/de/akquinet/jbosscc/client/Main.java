package de.akquinet.jbosscc.client;

import java.util.Properties;
import java.util.concurrent.Future;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.akquinet.jbosscc.asynchronous.AsynchronousService;

public class Main {

	public static void main(String[] args) throws Exception {
		Main main = new Main();
		AsynchronousService asynchronousService = main.lookup();

		Future<String> asyncResult = asynchronousService.asyncResult();

		while (!asyncResult.isDone()) {
			System.out.println("waiting...");

		}
		System.out.println(asyncResult.get());

		// https://issues.jboss.org/browse/AS7-3749
		asynchronousService.async();
	}

	public AsynchronousService lookup() throws NamingException {

		Properties props = new Properties();

		props.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.jboss.naming.remote.client.InitialContextFactory");
		props.put(Context.PROVIDER_URL, "remote://localhost:4447");
		props.put(Context.SECURITY_PRINCIPAL, "admin");
		props.put(Context.SECURITY_CREDENTIALS, "secret");

		final Context context = new InitialContext(props);
		final String appName = "";
		final String moduleName = "asynchronous";
		final String distinctName = "";
		final String beanName = "asynchronousService";
		final String viewClassName = AsynchronousService.class.getName();

		return (AsynchronousService) context.lookup("java:" + appName + "/"
				+ moduleName + "/" + distinctName + "/" + beanName + "!"
				+ viewClassName);
	}

}
