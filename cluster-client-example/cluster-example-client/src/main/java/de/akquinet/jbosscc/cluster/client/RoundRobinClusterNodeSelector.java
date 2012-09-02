package de.akquinet.jbosscc.cluster.client;

import org.jboss.ejb.client.ClusterNodeSelector;

public class RoundRobinClusterNodeSelector implements ClusterNodeSelector
{
    private int nodeIndex = 0;

    @Override
    public String selectNode(String clusterName, String[] connectedNodes, String[] availableNodes)
    {
//        String selectedNode = availableNodes[nodeIndex++ % availableNodes.length];
        String selectedNode;

        if (connectedNodes.length > 0) {
            selectedNode = connectedNodes[nodeIndex++ % connectedNodes.length];
        } else {
//            nodeIndex = 0;
            nodeIndex = availableNodes.length - 1;
            selectedNode = availableNodes[nodeIndex++];
        }
        System.out.println("RoundRobinClusterNodeSelector.selectNode => " + selectedNode);
        return selectedNode;
    }
}
