package de.akquinet.jbosscc.client.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import de.akquinet.jbosscc.client.JndiLookup;

public class MessagesReceiver implements MessageListener {

	private ConnectionFactory connectionFactory;

	private Queue queue;
	
	private String user;
	private String pass;

	public static void main(String[] args) throws Exception {

		MessagesReceiver reciever;
		
		switch (args.length) {
		case 4:
			reciever = new MessagesReceiver(args[0], args[1], args[2], args[3]);
			break;
		case 2:
			reciever = new MessagesReceiver(args[0], args[1]);
			break;
		default:
			reciever = new MessagesReceiver();
		}
		
		reciever.consum();

		while (true) {
			Thread.sleep(1000);
		}

	}

	public MessagesReceiver() throws Exception {
		this(null, null);
	}
	
	public MessagesReceiver(String ip, String port) throws Exception {
		this(ip, port, "admin", "secret");
	}

	public MessagesReceiver(String ip, String port, String user, String pass) throws Exception {
		this.user = user;
		this.pass = pass;
		JndiLookup jndiLookup = new JndiLookup(ip, port, user, pass);
		connectionFactory = jndiLookup.lookup(ConnectionFactory.class,
				"jms/RemoteConnectionFactory");
		queue = jndiLookup.lookup(Queue.class, "jms/queue/test");
	}

	private void consum() throws Exception {
		final Connection connection;;

		if (user != null && pass != null) {
			connection = connectionFactory.createConnection(user, pass);
		} else {
			connection = connectionFactory.createConnection();
		}
		
		final Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);

		final MessageConsumer consumer = session.createConsumer(queue);

		consumer.setMessageListener(this);

		connection.start();

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				try {
					consumer.close();
					session.close();
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void onMessage(Message message) {

		if (message instanceof TextMessage) {
			try {
				System.out.println("Received: "
						+ ((TextMessage) message).getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Received: " + message);
		}
	}

}
