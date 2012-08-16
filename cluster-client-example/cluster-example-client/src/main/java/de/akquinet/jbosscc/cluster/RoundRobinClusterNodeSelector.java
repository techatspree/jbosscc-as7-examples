package de.akquinet.jbosscc.cluster;

import org.jboss.ejb.client.ClusterNodeSelector;

public class RoundRobinClusterNodeSelector implements ClusterNodeSelector
{
    private int nodeIndex;

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
        System.out.println("selected node: " + selectedNode);
        return selectedNode;
    }
}
