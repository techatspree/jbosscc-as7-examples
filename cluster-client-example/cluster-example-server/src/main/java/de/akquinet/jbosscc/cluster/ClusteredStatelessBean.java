package de.akquinet.jbosscc.cluster;

import org.jboss.ejb3.annotation.Clustered;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

@Stateless
@Remote(RemoteStateless.class)
@Clustered
public class ClusteredStatelessBean implements RemoteStateless
{
    private final static Logger LOG = Logger.getLogger(ClusteredStatelessBean.class.getName());

    @Override
    public String getNodeName()
    {
        LOG.info("invoke getNodeName()");

        String nodeName = System.getProperty("jboss.node.name");
        try {
            return nodeName != null ? nodeName : InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
