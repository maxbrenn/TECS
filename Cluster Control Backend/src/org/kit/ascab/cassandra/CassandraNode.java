package org.kit.ascab.cassandra;

import java.net.InetAddress;
import org.kit.ascab.data.Node;

public class CassandraNode extends Node<CassandraNode>{

	public CassandraNode(String _name, InetAddress _inetAddress) {
		super(_name, _inetAddress);
		
	}

	public CassandraNode(String _name, String _inetAddressString) {
		super(_name, _inetAddressString);
		
	}
	
}
