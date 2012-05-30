package org.kit.tecs.test;

import org.kit.tecs.cassandra.CassandraIntegration;

public class CassandraIntegrationTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		CassandraIntegration ci = new CassandraIntegration("141.52.218.12");
		
		
		ci.dropKeyspace("testkeyspace");
		ci.createKeyspace("testkeyspace","testcolumnfamily");
		

	}

}
