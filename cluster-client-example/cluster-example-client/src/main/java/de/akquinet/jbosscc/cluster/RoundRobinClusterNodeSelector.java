package de.akquinet.jbosscc.cluster;

import org.jboss.ejb.client.ClusterNodeSelector;

public class RoundRobinClusterNodeSelector implements ClusterNodeSelector {

	private int nodeIndex;

	@Override
	public String selectNode(final String clusterName,
			final String[] connectedNodes, final String[] availableNodes) {

		String selectedNode = availableNodes[nodeIndex++ % availableNodes.length];
		System.out.println("selected node: " + selectedNode);
		return selectedNode;

	}

}
