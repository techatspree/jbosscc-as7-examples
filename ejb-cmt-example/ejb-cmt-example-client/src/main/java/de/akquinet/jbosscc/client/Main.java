package de.akquinet.jbosscc.client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.akquinet.jbosscc.CMTExample;
import de.akquinet.jbosscc.CMTExampleBean;

public class Main {

	public static void main(String[] args) throws Exception {
		Main main = new Main();
		CMTExample cmtExample = main.lookup();
		cmtExample.required();

		cmtExample.callMandatory();

		try {
			cmtExample.callNeverWithTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			cmtExample.callMandatoryWithoutTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public CMTExample lookup() throws NamingException {

		Properties props = new Properties();

		props.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.jboss.naming.remote.client.InitialContextFactory");
		props.put(Context.PROVIDER_URL, "remote://localhost:4447");
		props.put(Context.SECURITY_PRINCIPAL, "admin");
		props.put(Context.SECURITY_CREDENTIALS, "secret");

		final Context context = new InitialContext(props);
		final String appName = "";
		final String moduleName = "cmt";
		final String distinctName = "";
		final String beanName = CMTExampleBean.class.getSimpleName();
		final String viewClassName = CMTExample.class.getName();

		return (CMTExample) context.lookup("java:" + appName + "/" + moduleName
				+ "/" + distinctName + "/" + beanName + "!" + viewClassName);
	}
}
