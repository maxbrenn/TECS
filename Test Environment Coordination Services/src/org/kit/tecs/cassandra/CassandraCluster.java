package org.kit.tecs.cassandra;

import org.kit.tecs.data.Cluster;


public class CassandraCluster extends Cluster<CassandraCluster, CassandraNode> {

	public CassandraCluster(String _name, String propsFilePath) {
		super(_name, propsFilePath);
	
	}


	
}
