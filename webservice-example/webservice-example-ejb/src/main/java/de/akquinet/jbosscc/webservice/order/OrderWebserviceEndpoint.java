package de.akquinet.jbosscc.webservice.order;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@Stateless
@WebService//(targetNamespace="https://localhost:8443")
//@EndpointConfig(configName = "Standard WSSecurity Endpoint")
public class OrderWebserviceEndpoint implements OrderWebservice {

	@WebMethod
	public String order(@WebParam Product product) {
		return "Order product " + product.getName();
	}

}
