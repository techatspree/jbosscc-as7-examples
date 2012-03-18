package de.akquinet.jbosscc.client;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class MessageSender {

	private ConnectionFactory connectionFactory;
	private Queue queue;

	public static void main(String[] args) throws Exception {
		MessageSender sender = new MessageSender();
		sender.sendTextMessages("Hello World!");
	}

	public MessageSender() throws Exception {
		JndiLookup jndiLookup = new JndiLookup();
		connectionFactory = jndiLookup.lookup(ConnectionFactory.class,
				"jms/RemoteConnectionFactory");
		queue = jndiLookup.lookup(Queue.class, "jms/queue/test");
	}

	private void sendTextMessages(final String text) throws Exception {
		Connection connection = null;
		Session session = null;
		MessageProducer messageProducer = null;
		try {
			connection = connectionFactory
					.createConnection("admin", "secret");
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			messageProducer = session.createProducer(queue);
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
