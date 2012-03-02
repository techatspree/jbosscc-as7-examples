package de.akquinet.jbosscc.webservice.order;

import javax.ejb.Remote;

@Remote
public interface OrderWebservice {

	String order(Product product);

}
