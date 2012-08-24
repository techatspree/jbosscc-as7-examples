package de.akquinet.jbosscc.cluster.client;

import org.jboss.ejb.client.DeploymentNodeSelector;

public class RoundRobinDeploymentNodeSelector implements DeploymentNodeSelector
{
    private int nodeIndex = 0;

    @Override
    public String selectNode(String[] eligibleNodes, String appName, String moduleName,
                             String distinctName)
    {
        String selectedNode = eligibleNodes[nodeIndex++ % eligibleNodes.length];

        System.out.println("RoundRobinDeploymentNodeSelector.selectNode => " + selectedNode);
        return selectedNode;
    }
}
