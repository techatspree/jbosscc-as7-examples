package de.akquinet.jbosscc.webservice;

import java.net.URL;

import javax.xml.namespace.QName;

public class Main {

	public static void main(String[] args) throws Exception {
		QName qname = new QName("http://webservice.jbosscc.akquinet.de/",
				"GreetingWebserviceEndpointService");
		URL url = new URL(
				"http://localhost:8080/webservice-example-ejb-1.0-SNAPSHOT/GreetingWebserviceEndpoint?WSDL");

		GreetingWebserviceEndpointService service = new GreetingWebserviceEndpointService(
				url, qname);
		GreetingWebserviceEndpoint port = service
				.getGreetingWebserviceEndpointPort();
		System.out.println(port.sayHello());
	}

}
