package de.akquinet.jbosscc.cluster.client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import de.akquinet.jbosscc.cluster.ClusteredStateful;
import de.akquinet.jbosscc.cluster.ClusteredStateless;

public class Server {

	private Context context;

	public Server() throws Exception {
		Properties props = new Properties();
		props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		context = new InitialContext(props);
	}

	public ClusteredStateful getClusteredStatefulSession() throws Exception {

		return (ClusteredStateful) context
				.lookup("ejb:/cluster/ClusteredStatefulBean!de.akquinet.jbosscc.cluster.ClusteredStateful?stateful");
	}

	public ClusteredStateless getClusteredStatelessProxy() throws Exception {
		return (ClusteredStateless) context
				.lookup("ejb:/cluster/ClusteredStatelessBean!de.akquinet.jbosscc.cluster.ClusteredStateless");
	}
}
