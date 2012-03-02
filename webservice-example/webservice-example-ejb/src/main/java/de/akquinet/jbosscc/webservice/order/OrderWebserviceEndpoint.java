package de.akquinet.jbosscc.webservice.order;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@Stateless
@WebService
public class OrderWebserviceEndpoint implements OrderWebservice {

	@WebMethod
	public String order(@WebParam Product product){
		return "Order product " + product.getName();
	}

}
