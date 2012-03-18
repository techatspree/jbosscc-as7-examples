package de.akquinet.jbosscc.web;

import javax.ejb.Stateless;

@Stateless
public class GreetingBean {

	public String sayHello() {
		return "Hello World!";
	}

	public String sayHelloTo(String name) {
		return "Hello " + name + "!";
	}
}
