package org.kit.ascab.client;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.zookeeper.KeeperException;
import org.kit.ascab.zookeeper.ZooKeeperConnector;

public class NodeClient {

	protected Properties coordinationProperties;
	protected Properties nodeClientProperties;
	protected ZooKeeperConnector zookeeperConnector;
	protected InetAddress localhostAddress;

	public NodeClient() {
		
		coordinationProperties = parseProperties("conf/coordination.properties");
		
		nodeClientProperties = parseProperties("conf/nodeclient.properties");
	
//		zookeeperConnector = new ZooKeeperConnector(coordinationProperties.getProperty("coordination.hosts"));
		
//		copyDistributedPropertiesToLocal(nodeClientProperties.getProperty("node.properties.remote"),nodeClientProperties.getProperty("node.properties.local"));
		
		
		
		
		
		
		
		
	}

	private void copyDistributedPropertiesToLocal(String _remotePropsFilePath,
			String _localPropsFilePath) {

		try {

			BufferedWriter fileWriter = new BufferedWriter(new FileWriter(
					_localPropsFilePath));

			String remoteData = zookeeperConnector.read(_remotePropsFilePath,
					null);
			fileWriter.write(remoteData);

		} catch (InterruptedException | KeeperException | IOException e) {

			e.printStackTrace();
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

	
	
	
	public InetAddress getLocalhostAddress() {
		
		Pattern IPv4Pattern = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");
		InetAddress returnAddress = null;
				
		
		try {

			Enumeration<NetworkInterface> n = NetworkInterface
					.getNetworkInterfaces();
			for (; n.hasMoreElements();) {
				NetworkInterface e = n.nextElement();

				if (e.getName().equalsIgnoreCase("eth0")) {
					
					Enumeration<InetAddress> a = e.getInetAddresses();
					for (; a.hasMoreElements();) {
						InetAddress address = a.nextElement();

						if (IPv4Pattern.matcher(address.getHostAddress()).matches()) {
							returnAddress = address;
						}
					}
				}

			}

		} catch (SocketException e) {

			e.printStackTrace();
		}
		
		return returnAddress;
	}
	
	
}
