package de.akquinet.jbosscc.cluster.client.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.akquinet.jbosscc.cluster.client.gui.MainPresenter.Display;

public class MainDisplay extends JPanel implements Display {

	private static final long serialVersionUID = 1L;

	private GridBagConstraints mainConstraints = new GridBagConstraints();
	GridBagConstraints panelConstraints = new GridBagConstraints();
	private JPanel slsbPanel = new JPanel(new GridBagLayout());
	private JPanel sfsbPanel = new JPanel(new GridBagLayout());
	private JButton invokeStatelessProxyButton = new JButton("invoke");
	private JButton invokeSfsbButton = new JButton("invoke");
	private JButton createSfsbButton = new JButton("create");
	private JButton destroySfsbButton = new JButton("destroy");
	private JLabel counterValue = new JLabel("0");
	private JLabel currentSlsbNode = new JLabel();
	private JLabel currentSfsbNode = new JLabel();

	public MainDisplay() {
		super(new GridBagLayout());

		panelConstraints.anchor = GridBagConstraints.NORTHWEST;
		panelConstraints.gridx = 0;
		panelConstraints.gridy = 0;
		panelConstraints.weighty = 0.5;
		panelConstraints.weightx = 1;

		slsbPanel.setBorder(BorderFactory
				.createTitledBorder(("Stateless Session Bean")));
		slsbPanel.add(invokeStatelessProxyButton, panelConstraints);
		panelConstraints.gridy = 1;
		slsbPanel.add(currentSlsbNode, panelConstraints);

		sfsbPanel.setBorder(BorderFactory
				.createTitledBorder(("Stateful Session Bean")));

		mainConstraints.fill = GridBagConstraints.BOTH;
		mainConstraints.anchor = GridBagConstraints.NORTH;

		mainConstraints.gridx = 0;
		mainConstraints.gridy = 0;
		mainConstraints.weighty = 5;
		mainConstraints.weightx = 1;

		this.add(slsbPanel, mainConstraints);

		mainConstraints.gridy = 1;
		this.add(sfsbPanel, mainConstraints);
		sfsbPanel.add(createSfsbButton);
		sfsbPanel.add(destroySfsbButton);
		sfsbPanel.add(invokeSfsbButton);
		sfsbPanel.add(counterValue);
		sfsbPanel.add(currentSfsbNode);
	}

	public JComponent asComponent() {
		return this;
	}

	@Override
	public void setSlsbNode(final String result) {
		currentSlsbNode.setText(result);
	}

	@Override
	public void setSlsbActionListenet(ActionListener actionListener) {
		invokeStatelessProxyButton.addActionListener(actionListener);
	}

	@Override
	public void setCreateSfsbActionListenet(ActionListener actionListener) {
		createSfsbButton.addActionListener(actionListener);
	}

	@Override
	public void setDestroySfsbActionListenet(ActionListener actionListener) {
		destroySfsbButton.addActionListener(actionListener);
	}

	@Override
	public void setInvokeSfsbActionListenet(ActionListener actionListener) {
		invokeSfsbButton.addActionListener(actionListener);
	}

	@Override
	public void setCounterValue(final int value) {
		counterValue.setText(String.valueOf(value));
	}

	@Override
	public void setSfsbNode(final String node) {
		currentSfsbNode.setText(node);
	}

}
