package de.akquinet.jbosscc.cluster;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Named;

import org.jboss.ejb3.annotation.Clustered;

@Stateless
@Clustered
@Named
public class ClusteredStatelessBean {
	private final static Logger LOG = Logger.getLogger(ClusteredStatelessBean.class.getName());

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
