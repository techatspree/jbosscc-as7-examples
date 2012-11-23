package de.akquinet.jbosscc.client;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Producer {

	private String user;
	private String pass;

	private ConnectionFactory connectionFactory;
	private Queue queue;

	public static void main(String[] args) throws Exception {

		Producer sender;

		switch (args.length) {
		case 4:
			sender = new Producer(args[0], args[1], args[2], args[3]);
			break;
		case 2:
			sender = new Producer(args[0], args[1]);
			break;
		default:
			sender = new Producer();
		}

		sender.sendTextMessages("Message number ");
	}

	public Producer() throws Exception {
		this(null, null);
	}

	public Producer(String ip, String port) throws Exception {
		this(ip, port, "admin", "secret");
	}

	public Producer(String ip, String port, String user, String pass)
			throws Exception {
		this.user = user;
		this.pass = pass;
		JndiLookup jndiLookup = new JndiLookup(ip, port, user, pass);
		connectionFactory = jndiLookup.lookup(ConnectionFactory.class,
				"jms/RemoteConnectionFactory");
		queue = jndiLookup.lookup(Queue.class, "jms/queue/test");
	}

	private void sendTextMessages(final String text) throws Exception {
		Connection connection = null;
		Session session = null;
		MessageProducer messageProducer = null;
		try {
			if (user != null && pass != null) {
				connection = connectionFactory.createConnection(user, pass);
			} else {
				connection = connectionFactory.createConnection();
			}
			
			connection.setExceptionListener(new ExceptionListener() {
				
				@Override
				public void onException(JMSException exception) {
					exception.printStackTrace();
					
				}
			});
		
			
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			messageProducer = session.createProducer(queue);
			connection.start();
			TextMessage message = session.createTextMessage();

			int i = 0;
			while (i < 100) {
				message.setText(text + i++);
				messageProducer.send(message);
				Thread.sleep(250);
				System.out.println("send message " + i);
			}

		} finally {
			try {
				if (messageProducer != null)
					messageProducer.close();
				if (connection != null)
					connection.close();
				if (session != null)
					session.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

}
