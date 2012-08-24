package de.akquinet.jbosscc.cluster.client.gui;

import de.akquinet.jbosscc.cluster.client.gui.MainPresenter.Display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainDisplay extends JPanel implements Display
{
    private static final long serialVersionUID = 1L;

    // SLSB
    private JButton invokeSlsbProxyButton = new JButton("invoke");
    private JTextField currentSlsbNode = new JTextField(6);

    // SFSB
    private JButton invokeSfsbButton = new JButton("invoke");
    private JButton createSfsbButton = new JButton("create");

    private JButton destroySfsbButton = new JButton("destroy");
    private JTextField sfsbCounterValue = new JTextField(2);
    private JTextField currentSfsbNode = new JTextField(6);

    public MainDisplay()
    {
        super(new GridBagLayout());

        JPanel slsbPanel = new JPanel(new GridBagLayout());
        JPanel sfsbPanel = new JPanel(new GridBagLayout());

        GridBagConstraints mainConstraints = new GridBagConstraints();
        mainConstraints.fill = GridBagConstraints.BOTH;
        mainConstraints.anchor = GridBagConstraints.NORTH;
        mainConstraints.gridx = 0;
        mainConstraints.gridy = 0;
        mainConstraints.weighty = 5;
        mainConstraints.weightx = 1;

        this.add(slsbPanel, mainConstraints);

        mainConstraints.gridy = 1;
        this.add(sfsbPanel, mainConstraints);

        GridBagConstraints panelConstraints = new GridBagConstraints();
        panelConstraints.weighty = 1;
        panelConstraints.weightx = 1;

        // SLSB
        slsbPanel.setBorder(BorderFactory.createTitledBorder(("Stateless Session Bean")));

        panelConstraints.gridx = 0;
        panelConstraints.gridy = 0;
        panelConstraints.anchor = GridBagConstraints.EAST;
        slsbPanel.add(invokeSlsbProxyButton, panelConstraints);
        panelConstraints.gridy++;
        panelConstraints.anchor = GridBagConstraints.EAST;
        slsbPanel.add(new JLabel("SLSB Node Name:"), panelConstraints);
        panelConstraints.gridx++;
        panelConstraints.anchor = GridBagConstraints.WEST;
        slsbPanel.add(currentSlsbNode, panelConstraints);

        // SFSB
        sfsbPanel.setBorder(BorderFactory.createTitledBorder(("Stateful Session Bean")));

        panelConstraints.gridx = 0;
        panelConstraints.gridy = 0;
        panelConstraints.anchor = GridBagConstraints.EAST;
        sfsbPanel.add(createSfsbButton, panelConstraints);
        // Create and destroy share the same slot. Either one is invisible.
        sfsbPanel.add(destroySfsbButton, panelConstraints);

        panelConstraints.gridx++;
        panelConstraints.anchor = GridBagConstraints.WEST;
        sfsbPanel.add(invokeSfsbButton, panelConstraints);

        panelConstraints.gridy++;
        panelConstraints.gridx = 0;
        panelConstraints.anchor = GridBagConstraints.EAST;
        sfsbPanel.add(new JLabel("SFSB Node Name:"), panelConstraints);
        panelConstraints.gridx++;
        panelConstraints.anchor = GridBagConstraints.WEST;
        sfsbPanel.add(currentSfsbNode, panelConstraints);

        panelConstraints.gridx = 0;
        panelConstraints.gridy++;
        panelConstraints.anchor = GridBagConstraints.EAST;
        sfsbPanel.add(new JLabel("Counter Value:"), panelConstraints);
        panelConstraints.gridx++;
        panelConstraints.anchor = GridBagConstraints.WEST;
        sfsbPanel.add(sfsbCounterValue, panelConstraints);

        currentSlsbNode.setEditable(false);
        currentSlsbNode.setColumns(6);
        currentSfsbNode.setEditable(false);
        currentSfsbNode.setColumns(6);
        sfsbCounterValue.setEditable(false);
        sfsbCounterValue.setColumns(2);

        createSfsbButton.setVisible(true);
        destroySfsbButton.setVisible(false);
        invokeSfsbButton.setVisible(false);
    }


    public JComponent asComponent()
    {
        return this;
    }

    @Override
    public void setSlsbActionListener(ActionListener actionListener)
    {
        invokeSlsbProxyButton.addActionListener(actionListener);
    }

    @Override
    public void setCreateSfsbActionListener(ActionListener actionListener)
    {
        createSfsbButton.addActionListener(actionListener);
    }

    @Override
    public void setDestroySfsbActionListener(ActionListener actionListener)
    {
        destroySfsbButton.addActionListener(actionListener);
    }

    @Override
    public void setInvokeSfsbActionListener(ActionListener actionListener)
    {
        invokeSfsbButton.addActionListener(actionListener);
    }

    @Override
    public void setSlsbNode(final String result)
    {
        currentSlsbNode.setText(result);
    }

    @Override
    public void setSfsbCounterValue(final int value)
    {
        sfsbCounterValue.setText(String.valueOf(value));
    }

    @Override
    public void setSfsbNode(final String node)
    {
        currentSfsbNode.setText(node);
    }

    @Override
    public void toggleBetweenCreatableAndDestroyable()
    {
        createSfsbButton.setVisible(!createSfsbButton.isVisible());
        destroySfsbButton.setVisible(!destroySfsbButton.isVisible());
        invokeSfsbButton.setVisible(!invokeSfsbButton.isVisible());

        currentSfsbNode.setText("");
        sfsbCounterValue.setText("");
    }

}
