package de.akquinet.jbosscc.webservice;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
@Stateless
public class GreetingWebserviceEndpoint {

	public final static String NAME = "GreetingWebservice";

	@WebMethod
	public String sayHello() {
		return "Hello World";
	}
}
