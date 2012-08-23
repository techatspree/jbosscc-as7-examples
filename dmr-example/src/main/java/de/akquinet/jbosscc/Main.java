package de.akquinet.jbosscc;

import java.net.InetAddress;

import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.dmr.ModelNode;

public class Main {

	public static void main(String[] args) throws Exception {
		ModelControllerClient client = ModelControllerClient.Factory.create(InetAddress.getByName("localhost"),9999);

		ModelNode operation = new ModelNode();
		operation.get("operation").set("read-resource-description");
		operation.get("recursive").set(true);
		operation.get("operations").set(true);
		ModelNode address = operation.get("address");
		address.add("subsystem", "web");
		address.add("connector", "http");

		System.out.println(operation.toJSONString(false));

		ModelNode returnVal = client.execute(operation);
		System.out.println(returnVal.get("result").toString());

		client.close();
	}
}
