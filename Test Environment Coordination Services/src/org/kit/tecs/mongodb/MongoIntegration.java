package org.kit.tecs.mongodb;

import java.net.UnknownHostException;
import java.util.Set;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoException;

public class MongoIntegration {
	
	
	public static void main (String[] args) throws UnknownHostException, MongoException {
		
	BasicDBObject dbObject = new BasicDBObject();
	dbObject.put("user1", "max");
		
	
	Mongo m = new Mongo( "141.52.218.12" , 27017 );

	
	
	System.out.println(m.getDatabaseNames());
	
	DB db = m.getDB( "mydb" );
	
	System.out.println(m.getMongoOptions());
	Set<String> colls = db.getCollectionNames();

	for (String s : colls) {
	    System.out.println(s);
	}
		
	}
	
	
	
	
	

}
