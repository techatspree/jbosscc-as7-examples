package de.akquinet.jbosscc.cluster.client;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import de.akquinet.jbosscc.cluster.client.gui.MainPresenter;
import de.akquinet.jbosscc.cluster.client.gui.MainPresenterImpl;

public class Main {

	public static void main(String[] args) throws Exception {

		MainPresenter mainPresenter = new MainPresenterImpl();

		final JFrame frame = new JFrame("Example remote EJB client");
		Container content = frame.getContentPane();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				frame.setVisible(false);
				// Perform any other operations you might need
				// before exit.
				System.exit(0);
			}
		});

		content.add(mainPresenter.getComponent());
		frame.pack();
		frame.setSize(500, 250);
		frame.setVisible(true);

	}

}
