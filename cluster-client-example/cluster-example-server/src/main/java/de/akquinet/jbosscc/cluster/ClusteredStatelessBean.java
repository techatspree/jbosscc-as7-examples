package de.akquinet.jbosscc.cluster;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.Clustered;

@Stateless
@Remote(ClusteredStateless.class)
@Clustered
public class ClusteredStatelessBean implements ClusteredStateless {
	private final static Logger LOG = Logger.getLogger(ClusteredStatelessBean.class.getName());

	@Override
	public String getNodeName() {
		LOG.info("invoke getNodeName()");
		try {
			String jbossNodeName = System.getProperty("jboss.node.name");

			return jbossNodeName != null ? jbossNodeName : InetAddress
					.getLocalHost().getHostName();

		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}

	}

}
