package org.kit.tecs;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.kit.tecs.data.Cluster;
import org.kit.tecs.data.ClusterList;
import org.kit.tecs.data.ClusterType;
import org.kit.tecs.data.Node;
import org.kit.tecs.zookeeper.ZooKeeperConnector;

public class ClusterControl {

	
	
	protected Properties coordinationProperties;
	protected Properties clusterControlProperties;
	
	protected ZooKeeperConnector zookeeperConnector;
	
	protected ClusterList clusters;

	public ClusterControl() {

		
		coordinationProperties = parseProperties("conf/coordination.properties");
		clusterControlProperties = parseProperties("conf/clustercontrol.properties");
		
		clusters = parseClusters();

		printClusters();
		
		
		
		
	}

	public ClusterList getClusters() {
		return clusters;
	}

	public void setClusters(ClusterList clusters) {
		this.clusters = clusters;
	}

	private Properties parseProperties(String _propertiesFilename) {
		Properties _properties = new Properties();

		try {
			_properties.loadFromXML(new FileInputStream(_propertiesFilename));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Cannot load properties file: "
					+ _propertiesFilename);
		}

		return _properties;
	}

	private ClusterList parseClusters() {

		ClusterList _clusters = new ClusterList();
		String name;
		ClusterType clusterType;
		String pathToProperties;

		if (clusterControlProperties.getProperty("clustercount") != null) {

			for (int i = 1; i <= Integer.parseInt(clusterControlProperties
					.getProperty("clustercount")); i++) {

				name = "";
				clusterType = null;
				pathToProperties = "";

				if (clusterControlProperties.getProperty("cluster." + i + ".properties") != null) {

					pathToProperties = clusterControlProperties.getProperty("cluster." + i
							+ ".properties");

					if (clusterControlProperties.getProperty("cluster." + i + ".name") != null) {
						name = clusterControlProperties.getProperty("cluster." + i + ".name");
					} else {
						name = "cluster" + i;
					}

					_clusters.add(new Cluster(name, pathToProperties));

				} else {
					System.out.println("node." + i + ".host property not set!");
					System.exit(1);
				}

			}

		}

		return _clusters;

	}
	
	
	public void printClusters() {
		for(Cluster cluster: clusters) {
		for(Node node:cluster.getNodes()) {
			System.out.println(cluster.getName() + " - " + node.getName() + " - " + node.getInetAddress().getHostAddress());
		}
	}
	}

}
