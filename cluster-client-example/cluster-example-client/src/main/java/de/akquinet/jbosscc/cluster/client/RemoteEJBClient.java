package de.akquinet.jbosscc.cluster.client;

import de.akquinet.jbosscc.cluster.RemoteStateful;
import de.akquinet.jbosscc.cluster.ClusteredStatefulBean;
import de.akquinet.jbosscc.cluster.RemoteStateless;
import de.akquinet.jbosscc.cluster.ClusteredStatelessBean;
import org.jboss.ejb.client.ContextSelector;
import org.jboss.ejb.client.EJBClientContext;
import org.jboss.ejb.client.PropertiesBasedEJBClientConfiguration;
import org.jboss.ejb.client.remoting.ConfigBasedEJBClientContextSelector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class RemoteEJBClient
{
    /**
     * The application may be initialized either via the properties files jndi.properties and
     * jboss-ejb-client.properties, or purely programmatically without any properties files.
     * If this is set to true, an existing jboss-ejb-client.properties file may lead to conflicts.
     * Also, the initial servers must all be up and running (unlike when using
     * jboss-ejb-client.properties).
     */
    private static final boolean PROGRAMMATIC_INITIALIZATION = false;

    private final Context context;

    public RemoteEJBClient() throws NamingException
    {
        context = initializeJNDIContext();

        // Initialize EJB client context by setting a new context selector
        if (PROGRAMMATIC_INITIALIZATION) {
            initializeEJBClientContext();
        }
    }

    private Context initializeJNDIContext() throws NamingException
    {
        Context jndiContext;

        if (PROGRAMMATIC_INITIALIZATION) {
            Properties jndiProperties = new Properties();
            jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            jndiContext = new InitialContext(jndiProperties);
        } else {
            jndiContext = new InitialContext();
        }
        return jndiContext;
    }

    private void initializeEJBClientContext()
    {
        Properties properties = new Properties();

        properties.put("endpoint.name", "client-endpoint");

        properties.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "false");

        properties.put("remote.connections", "default");
        properties.put("remote.connection.default.host", "localhost");
        properties.put("remote.connection.default.port", "4447");
        properties.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", "false");

        PropertiesBasedEJBClientConfiguration configuration =
                new PropertiesBasedEJBClientConfiguration(properties);

        final ContextSelector<EJBClientContext> ejbClientContextSelector =
                new ConfigBasedEJBClientContextSelector(configuration);

        final ContextSelector<EJBClientContext> previousSelector =
                EJBClientContext.setSelector(ejbClientContextSelector);
    }

    public RemoteStateless lookupRemoteStatelessBean() throws NamingException
    {
        final String appName = "";
        final String moduleName = "cluster";  // module-name in ejb-jar.xml
        final String distinctName = "";  // jboss:distinct-name in jboss-ejb3.xml

        // ejb:/cluster//ClusteredStatelessBean!de.akquinet.jbosscc.cluster.RemoteStateless
        final String jndiName = "ejb:" + appName + '/' + moduleName + '/' + distinctName + '/' +
                ClusteredStatelessBean.class.getSimpleName() + '!' +
                RemoteStateless.class.getName();

        return (RemoteStateless) context.lookup(jndiName);
    }

    public RemoteStateful lookupRemoteStatefulBean() throws NamingException
    {
        final String appName = "";
        final String moduleName = "cluster";  // module-name in ejb-jar.xml
        final String distinctName = "";  // jboss:distinct-name in jboss-ejb3.xml

        // ejb:/cluster//ClusteredStatefulBean!de.akquinet.jbosscc.cluster.RemoteStateful?stateful
        final String jndiName = "ejb:" + appName + '/' + moduleName + '/' + distinctName + '/' +
                ClusteredStatefulBean.class.getSimpleName() + '!' +
                RemoteStateful.class.getName() + "?stateful";

        return (RemoteStateful) context.lookup(jndiName);
    }
}
