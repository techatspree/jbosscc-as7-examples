package de.akquinet.jbosscc.webservice;

import java.net.URL;

import javax.xml.namespace.QName;

public class Main {

	public static void main(String[] args) throws Exception {
//		System.setProperty("javax.net.ssl.trustStore", "/standalone/configuration/jboss-ssl.keystore");
		Main.sayHello();

		Product product = new Product();
		product.setName("Mac Book Pro");
		Main.order(product);
	}

	private static void sayHello() throws Exception {

		QName qname = new QName("http://webservice.jbosscc.akquinet.de/",
				"GreetingWebserviceEndpointService");
		URL url = new URL(
				"http://localhost:8080/webservice/GreetingWebserviceEndpoint?WSDL");

		GreetingWebserviceEndpointService service = new GreetingWebserviceEndpointService(
				url, qname);
		GreetingWebserviceEndpoint port = service
				.getGreetingWebserviceEndpointPort();
		System.out.println(port.sayHello());
	}

	private static void order(final Product product) throws Exception {
		QName qname = new QName("http://order.webservice.jbosscc.akquinet.de/",
				"OrderWebserviceEndpointService");
		URL url = new URL(
				"http://localhost:8080/webservice/OrderWebserviceEndpoint?WSDL");

		OrderWebserviceEndpointService service = new OrderWebserviceEndpointService(
				url, qname);
		OrderWebserviceEndpoint port = service.getOrderWebserviceEndpointPort();
		String order = port.order(product);
		System.out.println(order);
	}

}
