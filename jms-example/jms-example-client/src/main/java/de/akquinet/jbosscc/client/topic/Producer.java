package de.akquinet.jbosscc.client.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import de.akquinet.jbosscc.client.JndiLookup;

public class Producer {

	private ConnectionFactory connectionFactory;
	private Topic topic;

	public static void main(String[] args) throws Exception {
		Producer sender = new Producer();
		sender.sendTextMessages("Hello World!");
	}

	public Producer() throws Exception {
		JndiLookup jndiLookup = new JndiLookup();
		connectionFactory = jndiLookup.lookup(ConnectionFactory.class,
				"jms/RemoteConnectionFactory");
		topic = jndiLookup.lookup(Queue.class, "jms/topic/test");
	}

	private void sendTextMessages(final String text) throws Exception {
		Connection connection = null;
		Session session = null;
		MessageProducer messageProducer = null;
		try {
			connection = connectionFactory
					.createConnection("admin", "secret");
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			messageProducer = session.createProducer(topic);
			connection.start();
			TextMessage message = session.createTextMessage();

			message.setText(text);
			messageProducer.send(message);

		} finally {
			try {
				messageProducer.close();
				connection.close();
				session.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}

		}
	}

}
