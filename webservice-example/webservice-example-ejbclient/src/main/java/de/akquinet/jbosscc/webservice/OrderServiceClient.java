package de.akquinet.jbosscc.webservice;

import javax.ejb.Stateless;
import javax.xml.ws.WebServiceRef;

@Stateless
public class OrderServiceClient {

	@WebServiceRef(wsdlLocation = "http://localhost:8080/webservice-example-ejb-1.0-SNAPSHOT/OrderWebserviceEndpoint?WSDL")
	private OrderWebserviceEndpointService orderWebserviceEndpoint;

	public String order(String productname) {
		OrderWebserviceEndpoint port = orderWebserviceEndpoint
				.getOrderWebserviceEndpointPort();
		Product product = new Product();
		product.setName(productname);
		return port.order(product);
	}

}
