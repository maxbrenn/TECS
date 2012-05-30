package org.kit.tecs.zookeeper;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import org.apache.zookeeper.KeeperException;
import org.kit.tecs.data.Cluster;
import org.kit.tecs.data.ClusterType;
import org.kit.tecs.data.Node;


public class ZooKeeperCluster extends Cluster{
	
	
	ZooKeeperConnector dataAccessor;
	
	

	public ZooKeeperCluster(String _name, String propsFilePath) {
		super(_name, propsFilePath);
	
	}


	
	public String getHostsList() {
		
		
		
		
		
		Iterator<Node> nodeIter = nodes.iterator();
		
		String hostsList = nodeIter.next().getInetAddress().getHostAddress() + ":" + properties.getProperty("zookeeper.port");
		
		while(nodeIter.hasNext()) {
			hostsList = hostsList + "," + nodeIter.next().getInetAddress().getHostAddress() + ":" + properties.getProperty("zookeeper.port");
		}
		
		
		
		return hostsList;
		
		
	}
	

	

}
