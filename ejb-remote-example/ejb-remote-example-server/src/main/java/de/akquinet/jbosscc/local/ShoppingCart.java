package de.akquinet.jbosscc.local;

import javax.ejb.Local;

@Local
public interface ShoppingCart {

	void ckeckout();

	void add(String product, int amount);

}
