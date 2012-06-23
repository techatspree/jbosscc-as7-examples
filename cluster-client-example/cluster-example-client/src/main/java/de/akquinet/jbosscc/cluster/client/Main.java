package de.akquinet.jbosscc.cluster.client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import de.akquinet.jbosscc.cluster.ClusteredStateful;
import de.akquinet.jbosscc.cluster.ClusteredStateless;

public class Main {

	public static void main(String[] args) throws Exception {

		Properties props = new Properties();
		props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		Context context = new InitialContext(props);

		ClusteredStateless clusteredStatelessBeanProxy = (ClusteredStateless) context
				.lookup("ejb:/cluster/ClusteredStatelessBean!de.akquinet.jbosscc.cluster.ClusteredStateless");

		ClusteredStateful clusteredStatefulSession = getClusteredStatefulSession(context);

		for (int i = 0; i < 100; i++) {

			String nodeName = clusteredStatelessBeanProxy.getNodeName();
			System.out.println("nodename: " + nodeName);

			int counterValue = clusteredStatefulSession.getCounterValue();
			System.out.println("Stateful session bean - counter value "
					+ counterValue + " recieved from node: "
					+ clusteredStatefulSession.getNodeName() + " proxy "
					+ clusteredStatefulSession);
			if (counterValue % 3 == 0) {
				System.out.println("destroy stateful ejb on node: "
						+ clusteredStatefulSession.getNodeName());
				clusteredStatefulSession.destroy();
				clusteredStatefulSession = getClusteredStatefulSession(context);
			}
		}

	}

	static ClusteredStateful getClusteredStatefulSession(final Context context)
			throws Exception {

		return (ClusteredStateful) context
				.lookup("ejb:/cluster/ClusteredStatefulBean!de.akquinet.jbosscc.cluster.ClusteredStateful?stateful");
	}

}
