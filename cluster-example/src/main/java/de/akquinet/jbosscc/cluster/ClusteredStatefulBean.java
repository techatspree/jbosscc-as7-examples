package de.akquinet.jbosscc.cluster;

import java.util.logging.Logger;

import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.jboss.ejb3.annotation.Clustered;

@Stateful
@SessionScoped
@Clustered
@Named
public class ClusteredStatefulBean {

	private final static Logger LOG = Logger
			.getLogger(ClusteredStatefulBean.class.getName());

	private int counter;

	public int getCounterValue() {
		LOG.info("invoke getCounter()");
		return counter++;
	}

	@PrePassivate
	public void passivate() {
		LOG.info("passivate ejb component: " + this);
	}

	@PostActivate
	public void activate() {
		LOG.info("activate ejb component: " + this);
	}
}
