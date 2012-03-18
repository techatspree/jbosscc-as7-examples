package de.akquinet.jbosscc.client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JndiLookup {

	@SuppressWarnings("unchecked")
	public <T> T lookup(Class<?> type, String jndiName) throws NamingException {

		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.jboss.naming.remote.client.InitialContextFactory");
		props.put(Context.PROVIDER_URL, "remote://localhost:4447");
		props.put(Context.SECURITY_PRINCIPAL, "admin");
		props.put(Context.SECURITY_CREDENTIALS, "secret");

		final Context context = new InitialContext(props);

		return (T) context.lookup(jndiName);
	}

}
