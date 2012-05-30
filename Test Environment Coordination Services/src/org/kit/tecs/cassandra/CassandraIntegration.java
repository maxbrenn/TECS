package org.kit.tecs.cassandra;

import java.nio.ByteBuffer;
import java.util.Arrays;
import org.apache.cassandra.locator.SimpleStrategy;
import org.apache.cassandra.thrift.Cassandra;
import org.apache.cassandra.thrift.CfDef;
import org.apache.cassandra.thrift.ColumnDef;
import org.apache.cassandra.thrift.Compression;
import org.apache.cassandra.thrift.InvalidRequestException;
import org.apache.cassandra.thrift.KsDef;
import org.apache.cassandra.thrift.SchemaDisagreementException;
import org.apache.cassandra.thrift.TBinaryProtocol;
import org.apache.cassandra.thrift.TimedOutException;
import org.apache.cassandra.thrift.UnavailableException;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;


public class CassandraIntegration {

	TTransport tr;
	TProtocol proto;
	Cassandra.Client client;

	public CassandraIntegration(String _hostAddress) {
		tr = new TFramedTransport(new TSocket(_hostAddress, 9160));
		proto = new TBinaryProtocol(tr);
		client = new Cassandra.Client(proto);
	}

	public void createKeyspace(String _ksName, int _repFac, String _stratClass, String _cfName) {


		try {
			tr.open();

			String cql ="create keyspace " + _ksName + " with  strategy_class=" + _stratClass + " and strategy_options:replication_factor=" + _repFac + ";";
	        client.execute_cql_query(ByteBuffer.wrap(cql.getBytes()), Compression.NONE);
			
	        cql ="use " + _ksName + ";";
	        client.execute_cql_query(ByteBuffer.wrap(cql.getBytes()), Compression.NONE);
	        
	        cql ="create columnfamily " + _cfName + " (KEY text PRIMARY KEY);";
	        client.execute_cql_query(ByteBuffer.wrap(cql.getBytes()), Compression.NONE);
	        
			tr.close();

		} catch (InvalidRequestException | SchemaDisagreementException| UnavailableException | TimedOutException
				| TException e) {

			e.printStackTrace();
		} 

	}

	public void createKeyspace(String _ksName, String _cfName) {
		
		createKeyspace(_ksName,1,"SimpleStrategy",_cfName);

	}
	
	public void dropKeyspace(String _ksName) {
		
        try {
        	tr.open();
        	
        	String cql ="drop keyspace " + _ksName + ";";
			client.execute_cql_query(ByteBuffer.wrap(cql.getBytes()), Compression.NONE);
			
			tr.close();
		} catch (InvalidRequestException | UnavailableException
				| TimedOutException | SchemaDisagreementException | TException e) {
			
			e.printStackTrace();
		}
	}

}
