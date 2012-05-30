package org.kit.tecs.zookeeper;

import java.net.InetAddress;

import org.kit.tecs.data.Node;

public class ZooKeeperNode extends Node<ZooKeeperNode>{

	public ZooKeeperNode(String _name, InetAddress _inetAddress) {
		super(_name, _inetAddress);
		
	}
	
	public ZooKeeperNode(String _name, String _inetAddressString) {
		super(_name, _inetAddressString);
		
	}

}
