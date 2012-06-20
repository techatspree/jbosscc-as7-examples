package de.akquinet.jbosscc.cluster;

import javax.ejb.Remote;

@Remote
public interface ClusteredStateless {

	String getNodeName();

}
