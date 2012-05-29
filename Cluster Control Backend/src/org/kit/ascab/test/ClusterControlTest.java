package org.kit.ascab.test;

import org.apache.zookeeper.KeeperException;
import org.kit.ascab.ClusterControl;
import org.kit.ascab.ClusterDataDistributor;
import org.kit.ascab.data.Cluster;
import org.kit.ascab.data.Node;
import org.kit.ascab.zookeeper.ZooKeeperCluster;
import org.kit.ascab.zookeeper.ZooKeeperConnector;
import org.kit.ascab.zookeeper.ZooKeeperNode;


public class ClusterControlTest {
	
	static ClusterControlTest instance = new ClusterControlTest();

	public static void main(String[] args) {
		
		ClusterControl clusterControl = new ClusterControl();

//		Cluster<CassandraCluster, CassandraNode> cassandraCluster = new Cluster<CassandraCluster, CassandraNode>(
//				"Cassandra Test Cluster", "conf/cassandra.properties");
//
//		cassandraCluster.printNodesInfo();
//
			
		

		
//		ZooKeeperCluster zookeeperCluster = new ZooKeeperCluster("ZooKeeper Cluster" , "conf/zookeeper.properties");
//		zookeeperCluster.printNodesInfo();
//	
		ZooKeeperConnector zookeeperConnector = new ZooKeeperConnector("141.52.218.12");
		zookeeperConnector.observerNode("/clusters/YCSB/nodes/workload/inc_wrt_cons",instance.new UpdateWorkloadProperties());
		
//		ClusterDataDistributor clusterDataDistributor = new ClusterDataDistributor(zookeeperConnector);
		
//		clusterDataDistributor.distributeDataFile("data/distribution/cassandra/cassandra.yaml", "/clusters/cassandra/nodes/1/conf/cassandra.yaml", null);
		
		
//			zookeeperConnector.writeNodeData("/clusters/ycsb/nodes/4/stat","" , false);
		
		
	}
	public abstract class ControllerOperation {
		
		public abstract void execute();
	}

	public class UpdateWorkloadProperties extends ControllerOperation {
		
		public UpdateWorkloadProperties() {
			super();
		}

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			
		}
		
	}
}

