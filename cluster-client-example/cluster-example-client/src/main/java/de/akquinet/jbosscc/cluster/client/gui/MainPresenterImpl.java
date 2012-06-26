package de.akquinet.jbosscc.cluster.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import de.akquinet.jbosscc.cluster.ClusteredStateful;
import de.akquinet.jbosscc.cluster.ClusteredStateless;
import de.akquinet.jbosscc.cluster.client.Server;

public class MainPresenterImpl implements MainPresenter {

	private final Display display;
	private final Server server;

	private ClusteredStateful clusteredStatefulSession;
	private ClusteredStateless clusteredStatelessProxy;

	public MainPresenterImpl() throws Exception {
		super();

		this.display = new MainDisplay();
		server = new Server();
		bind();
	}

	private void bind() throws Exception {
		clusteredStatelessProxy = server.getClusteredStatelessProxy();

		display.setCreateSfsbActionListenet(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					clusteredStatefulSession = server
							.getClusteredStatefulSession();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		display.setDestroySfsbActionListenet(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				clusteredStatefulSession.destroy();

			}
		});

		display.setInvokeSfsbActionListenet(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				int counterValue = clusteredStatefulSession.getCounterValue();
				display.setCounterValue(counterValue);
				display.setSfsbNode(clusteredStatefulSession.getNodeName());
			}
		});

		display.setSlsbActionListenet(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				display.setSlsbNode(clusteredStatelessProxy.getNodeName());

			}
		});

	}

	@Override
	public JComponent getComponent() {
		return display.asComponent();
	}

}
