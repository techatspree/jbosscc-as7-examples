package de.akquinet.jbosscc.client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JndiLookup {

	private static final String STD_IP = "127.0.0.1";
	private static final String STD_PORT = "4447";

	private final Context context;

	public JndiLookup() throws NamingException {
		this(STD_IP, STD_PORT);
	}

	public JndiLookup(String ip, String port) throws NamingException {
		this(STD_IP, STD_PORT, null, null);
	}

	public JndiLookup(String ip, String port, String user, String pass)
			throws NamingException {
		if (ip == null || port == null) {
			ip = STD_IP;
			port = STD_PORT;
		}

		final Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.jboss.naming.remote.client.InitialContextFactory");
		props.put(Context.PROVIDER_URL, "remote://" + ip + ":" + port);

		if (user != null && pass != null) {
			props.put(Context.SECURITY_PRINCIPAL, user);
			props.put(Context.SECURITY_CREDENTIALS, pass);
		}

		context = new InitialContext(props);
	}

	@SuppressWarnings("unchecked")
	public <T> T lookup(Class<?> type, String jndiName) throws NamingException {

		return (T) context.lookup(jndiName);
	}

}
