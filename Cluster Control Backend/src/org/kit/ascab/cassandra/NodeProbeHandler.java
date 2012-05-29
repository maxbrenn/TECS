package org.kit.ascab.cassandra;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.cassandra.config.ConfigurationException;

public class NodeProbeHandler {

	NodeProbe nodeProbe;
	Properties properties;

	public NodeProbeHandler() {

		properties = new Properties();
		try {
			properties.loadFromXML(new FileInputStream("cassandra.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {

			nodeProbe = new NodeProbe(properties.getProperty("host"),
					Integer.parseInt(properties.getProperty("port")),
					properties.getProperty("user"),
					properties.getProperty("password"));

		} catch (NumberFormatException | IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}

	public String getGossip() {

		return nodeProbe.getGossipInfo();

	}

	public List<String> getLiveNodes() {

		
			return nodeProbe.getLiveNodes();

	}

	public String getNodeInfo(String host) {

		try {

			NodeProbe nodeProbe = new NodeProbe(host, Integer.parseInt(properties.getProperty("jmxport")), properties.getProperty("username"), properties.getProperty("password"));

			String keyspaceString = "";

			for (String keyspace : nodeProbe.getKeyspaces()) {
				keyspaceString = keyspaceString + keyspace + " ";
			}

			String returnString = "Adress:          " + nodeProbe.getEndpoint()
					+ "\n" + "Token:           " + nodeProbe.getToken()
					+ "\n\n" + "Operation Mode:  "
					+ nodeProbe.getOperationMode() + "\n" + "Load:            "
					+ nodeProbe.getLoadString() + "\n" + "Uptime:          "
					+ nodeProbe.getUptime() + "\n\n" + "Keyspaces: 	 "
					+ keyspaceString + "\n" + "Schema Version:  "
					+ nodeProbe.getSchemaVersion() + "\n\n"
					+ "Data Center:     " + nodeProbe.getDataCenter() + "\n"
					+ "Rack:            " + nodeProbe.getRack() + "\n";

			
			nodeProbe.close();
			
			
			return returnString;

		} catch (IOException | InterruptedException e) {

			return e.getMessage();
		}

	}

	public String moveNode(String host, String newToken) {

		try {

			NodeProbe nodeProbe = new NodeProbe(host,  Integer.parseInt(properties.getProperty("jmxport")), properties.getProperty("username"), properties.getProperty("password"));

			String oldToken = nodeProbe.getToken();

			nodeProbe.move(newToken);

			nodeProbe.close();
			
			return "Successfully moved Node from \n" + "old Token: " + oldToken
					+ " to \n" + "new Token: " + newToken;

		} catch (IOException | InterruptedException e) {

			return e.getMessage();
		} catch (ConfigurationException e) {

			return e.getMessage();
		}

	}

}
