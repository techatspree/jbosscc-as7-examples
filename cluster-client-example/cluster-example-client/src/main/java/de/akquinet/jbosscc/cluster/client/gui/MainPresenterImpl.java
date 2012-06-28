package de.akquinet.jbosscc.cluster.client.gui;

import de.akquinet.jbosscc.cluster.ClusteredStateful;
import de.akquinet.jbosscc.cluster.ClusteredStateless;
import de.akquinet.jbosscc.cluster.client.Server;
import org.jdesktop.swingx.JXErrorPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        display.setCreateSfsbActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    clusteredStatefulSession = server
                            .getClusteredStatefulSession();
                    display.toggleBetweenCreatableAndDestroyable();
                } catch (Exception e) {
                    e.printStackTrace();
                    JXErrorPane.showDialog(e);
                }
            }
        });

        display.setDestroySfsbActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    clusteredStatefulSession.destroy();
                } catch (Exception e) {
                    e.printStackTrace();
                    JXErrorPane.showDialog(e);
                }
                display.toggleBetweenCreatableAndDestroyable();
            }
        });

        display.setInvokeSfsbActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    int counterValue = clusteredStatefulSession.getCounterValue();
                    display.setSfsbCounterValue(counterValue);
                    String nodeName = clusteredStatefulSession.getNodeName();
                    display.setSfsbNode(nodeName);
                } catch (Exception e) {
                    e.printStackTrace();
                    JXErrorPane.showDialog(e);
                }
            }
        });

        display.setSlsbActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    String nodeName = clusteredStatelessProxy.getNodeName();
                    display.setSlsbNode(nodeName);
                } catch (Exception e) {
                    e.printStackTrace();
                    JXErrorPane.showDialog(e);
                }
            }
        });
    }

    @Override
    public JComponent getComponent() {
        return display.asComponent();
    }
}
