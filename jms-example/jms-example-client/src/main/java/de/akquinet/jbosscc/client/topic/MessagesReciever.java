package de.akquinet.jbosscc.client.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;

import de.akquinet.jbosscc.client.JndiLookup;

public class MessagesReciever implements MessageListener {

	private ConnectionFactory connectionFactory;

	private Topic topic;

	public static void main(String[] args) throws Exception {

		MessagesReciever reciever = new MessagesReciever();
		reciever.consum();

		while (true) {
			Thread.sleep(1000);
		}

	}

	public MessagesReciever() throws Exception {
		JndiLookup jndiLookup = new JndiLookup();
		connectionFactory = jndiLookup.lookup(ConnectionFactory.class,
				"jms/RemoteConnectionFactory");

		topic = jndiLookup.lookup(Topic.class, "jms/topic/test");
	}

	private void consum() throws Exception {
		Connection connection = connectionFactory.createConnection("admin",
				"secret");

		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);

		MessageConsumer consumer = session.createConsumer(topic);

		consumer.setMessageListener(this);

		connection.start();

	}

	@Override
	public void onMessage(Message message) {

		System.out.println("Received: " + message);
	}

}
