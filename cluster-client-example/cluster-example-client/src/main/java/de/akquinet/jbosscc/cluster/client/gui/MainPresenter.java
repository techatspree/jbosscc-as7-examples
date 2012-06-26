package de.akquinet.jbosscc.cluster.client.gui;

import java.awt.event.ActionListener;

import javax.swing.JComponent;

public interface MainPresenter {
	interface Display {
		JComponent asComponent();

		void setSlsbNode(String result);

		void setSlsbActionListenet(ActionListener actionListener);

		void setCreateSfsbActionListenet(ActionListener actionListener);

		void setInvokeSfsbActionListenet(ActionListener actionListener);

		void setDestroySfsbActionListenet(ActionListener actionListener);

		void setCounterValue(int value);

		void setSfsbNode(String node);

	}

	JComponent getComponent();
}
