package de.akquinet.jbosscc.local;

public interface ShoppingCartManagementMXBean {

	void checkout();

	void add(String product, Integer amount);

	void createCart();

}
