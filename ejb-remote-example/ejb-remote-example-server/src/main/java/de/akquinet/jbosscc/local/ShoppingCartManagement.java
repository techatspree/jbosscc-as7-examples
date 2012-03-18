package de.akquinet.jbosscc.local;

import java.lang.management.ManagementFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.management.MBeanServer;
import javax.management.ObjectName;

@Singleton
@Startup
public class ShoppingCartManagement implements ShoppingCartManagementMXBean {

	@Resource
	private SessionContext sessionContext;

	private MBeanServer platformMBeanServer;
	private ObjectName objectName;

	private ShoppingCart shoppingCart;

	@PostConstruct
	public void register() {
		try {
			objectName = new ObjectName("de.akquinet.jbosscc:shopping="
					+ this.getClass().getName());
			platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
			platformMBeanServer.registerMBean(sessionContext
					.getBusinessObject(ShoppingCartManagementMXBean.class),
					objectName);
		} catch (Exception e) {
			throw new IllegalStateException("Problem during registration" + e);
		}
	}

	@PreDestroy
	public void unregister() {
		try {
			platformMBeanServer.unregisterMBean(this.objectName);
		} catch (Exception e) {
			throw new IllegalStateException(
					"Problem during unregistration of Monitoring into JMX:" + e);
		}
	}

	public void add(String product, Integer amount) {
		shoppingCart.add(product, amount);
	}

	public void checkout() {
		shoppingCart.ckeckout();
	}

	@Override
	public void createCart() {
		shoppingCart = (ShoppingCart) sessionContext.lookup("java:global/remote/ShoppingCartBean");

	}

}
