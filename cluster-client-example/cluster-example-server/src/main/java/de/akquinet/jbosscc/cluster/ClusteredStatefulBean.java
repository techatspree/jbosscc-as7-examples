package de.akquinet.jbosscc.cluster;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import org.jboss.ejb3.annotation.Clustered;

@Stateful
@Clustered
@Remote(ClusteredStateful.class)
public class ClusteredStatefulBean implements ClusteredStateful{

	private final static Logger LOG = Logger
			.getLogger(ClusteredStatefulBean.class.getName());

	private int counter = 1;

	@Override
	public int getCounterValue() {
		LOG.info("invoke getCounter()");
		return counter++;
	}

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

	@PrePassivate
	@Override
	public void passivate() {
		LOG.info("passivate ejb component: " + this);
	}

	@PostActivate
	@Override
	public void activate() {
		LOG.info("activate ejb component: " + this);
	}


	@Remove
	@Override
	public void destroy(){
		LOG.info("destroy stateful ejb component: " + this);
	}
}
