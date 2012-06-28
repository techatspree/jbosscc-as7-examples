package de.akquinet.jbosscc.cluster.client;

import de.akquinet.jbosscc.cluster.client.gui.MainPresenter;
import de.akquinet.jbosscc.cluster.client.gui.MainPresenterImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args) throws Exception {

        MainPresenter mainPresenter = new MainPresenterImpl();

        final JFrame frame = new JFrame("Example remote EJB client");
        Container content = frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
                // Perform any other operations you might need before exit.
                System.exit(0);
            }
        });

        content.add(mainPresenter.getComponent());
        frame.pack();
        frame.setSize(300, 250);

        // Center frame on screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        int centerPosX = (screenSize.width - frameSize.width) / 2;
        int centerPosY = (screenSize.height - frameSize.height) / 2;
        frame.setLocation(centerPosX, centerPosY);

        frame.setVisible(true);
        frame.setResizable(false);
    }
}
