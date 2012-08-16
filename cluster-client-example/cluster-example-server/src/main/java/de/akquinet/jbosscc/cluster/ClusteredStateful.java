package de.akquinet.jbosscc.cluster;

public interface ClusteredStateful {

	void destroy();

	void activate();

	void passivate();

	int getCounterValue();

	String getNodeName();
}
