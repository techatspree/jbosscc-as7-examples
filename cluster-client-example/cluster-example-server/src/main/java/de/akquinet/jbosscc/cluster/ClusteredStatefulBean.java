package de.akquinet.jbosscc.cluster;

import org.jboss.ejb3.annotation.Clustered;

import javax.ejb.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

@Stateful
@Clustered
@Remote(ClusteredStateful.class)
public class ClusteredStatefulBean implements ClusteredStateful
{
    private final static Logger LOG = Logger.getLogger(ClusteredStatefulBean.class.getName());

    private int counter = 1;

    @Override
    public int getCounterValue()
    {
        LOG.info("invoke getCounter()");
        return counter++;
    }

    @Override
    public String getNodeName()
    {
        LOG.info("invoke getNodeName()");
        try {
            String nodeName = System.getProperty("jboss.node.name");

            return nodeName != null ? nodeName : InetAddress.getLocalHost().getHostName();

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    @PrePassivate
    @Override
    public void passivate()
    {
        LOG.info("passivate ejb component: " + this);
    }

    @PostActivate
    @Override
    public void activate()
    {
        LOG.info("activate ejb component: " + this);
    }

    @Remove
    @Override
    public void destroy()
    {
        LOG.info("destroy stateful ejb component: " + this);
    }
}
