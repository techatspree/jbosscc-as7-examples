package de.akquinet.jbosscc.singelton;

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
public class ApplicationBootstrapManagement implements
		ApplicationBootstrapManagementMXBean {

	@Resource
	private SessionContext sessionContext;

	private MBeanServer platformMBeanServer;
	private ObjectName objectName;

	private String configuration;

	@PostConstruct
	public void register() {
		try {
			objectName = new ObjectName("de.akquinet.jbosscc:configuration="
					+ this.getClass().getName());
			platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
			platformMBeanServer
					.registerMBean(
							sessionContext
									.getBusinessObject(ApplicationBootstrapManagementMXBean.class),
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

	public String getConfiguration() {
		return configuration;
	}

	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}




}
