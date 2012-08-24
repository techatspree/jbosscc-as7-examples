package de.akquinet.jbosscc.cluster;

import javax.ejb.Remote;

@Remote
public interface RemoteStateless
{
    String getNodeName();
}
