package de.akquinet.jbosscc.cluster.client.gui;

import de.akquinet.jbosscc.cluster.RemoteStateful;
import de.akquinet.jbosscc.cluster.RemoteStateless;
import de.akquinet.jbosscc.cluster.client.RemoteEJBClient;
import org.jdesktop.swingx.JXErrorPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPresenterImpl implements MainPresenter
{
    private final Display display;

    private final RemoteEJBClient remoteEJBClient;

    private RemoteStateless statelessProxy;
    private RemoteStateful statefulSession;

    public MainPresenterImpl() throws Exception
    {
        display = new MainDisplay();

        remoteEJBClient = new RemoteEJBClient();

        try {
            statelessProxy = remoteEJBClient.lookupRemoteStatelessBean();
        } catch (Exception e) {
            e.printStackTrace();
            JXErrorPane.showDialog(e);
        }

        display.setSlsbActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                try {
                    String nodeName = statelessProxy.getNodeName();
                    display.setSlsbNode(nodeName);
                } catch (Exception e) {
                    e.printStackTrace();
                    JXErrorPane.showDialog(e);
                }
            }
        });

        display.setCreateSfsbActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                try {
                    statefulSession = remoteEJBClient.lookupRemoteStatefulBean();
                    display.toggleBetweenCreatableAndDestroyable();
                } catch (Exception e) {
                    e.printStackTrace();
                    JXErrorPane.showDialog(e);
                }
            }
        });

        display.setDestroySfsbActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                try {
                    statefulSession.destroy();
                } catch (Exception e) {
                    e.printStackTrace();
                    JXErrorPane.showDialog(e);
                }
                display.toggleBetweenCreatableAndDestroyable();
            }
        });

        display.setInvokeSfsbActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                try {
                    int counterValue = statefulSession.getAndIncrementCounter();
                    display.setSfsbCounterValue(counterValue);
                    String nodeName = statefulSession.getNodeName();
                    display.setSfsbNode(nodeName);
                } catch (Exception e) {
                    e.printStackTrace();
                    JXErrorPane.showDialog(e);
                }
            }
        });
    }

    @Override
    public JComponent getComponent()
    {
        return display.asComponent();
    }
}
