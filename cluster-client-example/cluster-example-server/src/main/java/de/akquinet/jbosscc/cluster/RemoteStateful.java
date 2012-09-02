package de.akquinet.jbosscc.cluster;

public interface RemoteStateful
{
    int getAndIncrementCounter();
    String getNodeName();

    void activate();
    void passivate();
    void destroy();
}
