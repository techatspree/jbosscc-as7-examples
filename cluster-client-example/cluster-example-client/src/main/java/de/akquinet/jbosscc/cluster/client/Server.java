package de.akquinet.jbosscc.cluster.client;

import de.akquinet.jbosscc.cluster.ClusteredStateful;
import de.akquinet.jbosscc.cluster.ClusteredStateless;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Server
{
    private final Context context;

    public Server() throws NamingException
    {
//        Properties jndiProperties = new Properties();
//        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
//        context = new InitialContext(jndiProperties);
        context = new InitialContext();
    }

    public ClusteredStateless getClusteredStatelessProxy() throws Exception
    {
        return (ClusteredStateless) context.lookup(
                "ejb:/cluster//ClusteredStatelessBean!de.akquinet.jbosscc.cluster.ClusteredStateless");
    }

    public ClusteredStateful getClusteredStatefulSession() throws Exception
    {
        return (ClusteredStateful) context.lookup(
                "ejb:/cluster//ClusteredStatefulBean!de.akquinet.jbosscc.cluster.ClusteredStateful?stateful");
    }
}
