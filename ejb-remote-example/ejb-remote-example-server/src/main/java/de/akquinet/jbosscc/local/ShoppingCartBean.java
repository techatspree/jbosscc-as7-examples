package de.akquinet.jbosscc.local;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;

import de.akquinet.jbosscc.interceptor.LoggingInterceptor;

/**
 *
 * https://issues.jboss.org/browse/AS7-3830
 *
 */

@Stateful
@Interceptors(value = { LoggingInterceptor.class })
public class ShoppingCartBean implements ShoppingCart {

	private Map<String, Integer> cart = new HashMap<String, Integer>();

	public void add(final String product, final int amount) {
		cart.put(product, amount);
	}

	@Remove
	public void ckeckout() {
		for (Entry<String, Integer> entry : cart.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}

}
