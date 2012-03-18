package de.akquinet.jbosscc.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/test")})
public class ExampleMDB implements MessageListener {

	@Override
	public void onMessage(Message message) {

		System.out.println("----------------");
		System.out.println("Received message");
		System.out.println("----------------");

	}

}
