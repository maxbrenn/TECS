package org.kit.tecs.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.kit.tecs.data.Node;

public class Cluster<C,N> {

	protected String name;
	protected Properties properties;
	protected NodeList nodes;
	

	public Cluster(String _name, String propsFilePath) {

		name = _name;
		properties = parseProperties(propsFilePath);
		nodes = parseNodes();	

	}
	
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public NodeList getNodes() {
		return nodes;
	}

	public void setNodes(NodeList nodes) {
		this.nodes = nodes;
	}

	public void addNode(Node<N> _node) {
		this.nodes.add(_node);
	}

	public void printNodesInfo() {

		for (Node<N> node : nodes) {
			System.out.println(node.name + ": "
					+ node.inetAddress.getHostAddress());
		}

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

	private NodeList parseNodes() {

		NodeList _nodes = new NodeList();
		String host;
		String name;

		if (properties.getProperty("nodecount") != null) {

			for (int i = 1; i <= Integer.parseInt(properties
					.getProperty("nodecount")); i++) {

				host = "";
				name = "";

				if (properties.getProperty("node." + i + ".host") != null) {

					host = properties.getProperty("node." + i + ".host");

					if (properties.getProperty("node." + i + ".name") != null) {
						name = properties.getProperty("node." + i + ".name");
					} else {
						name = "node" + i;
					}
					
					_nodes.add(new Node<N>(name,host));

				} else {
					System.out.println("node." + i + ".host property not set!");
					System.exit(1);
				}

			}

		}

		return _nodes;

	}
	
	
	

}
