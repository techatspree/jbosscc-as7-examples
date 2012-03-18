package de.akquinet.jbosscc.singelton;

import javax.ejb.Local;

@Local
public interface ApplicationBootstrapManagementMXBean {

	void register();

	void unregister();

	String getConfiguration();

	void setConfiguration(String configuration);

}
