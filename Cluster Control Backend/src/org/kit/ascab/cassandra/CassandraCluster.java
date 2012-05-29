package org.kit.ascab.cassandra;

import org.kit.ascab.data.Cluster;


public class CassandraCluster extends Cluster<CassandraCluster, CassandraNode> {

	public CassandraCluster(String _name, String propsFilePath) {
		super(_name, propsFilePath);
	
	}


	
}
