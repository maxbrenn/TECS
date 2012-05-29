package org.kit.ascab.zookeeper;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import org.apache.zookeeper.KeeperException;

public class DataUpdater {
	LogUtil zkLogger = new LogUtil(this.getClass());

	public static void main(String[] args) throws IOException,
			InterruptedException, KeeperException {

		
//		ZooKeeperConnector dataAccessor = new ZooKeeperConnector();
//		dataAccessor.connect(properties.getProperty("zk.hosts"));
//		dataAccessor.write(properties.getProperty("zk.directory.nodestatus"), "Zeit: " + new Date());
//		System.out.printf("Set %s to %s\n", "/zoo", "Zeit: " + new Date());
//		dataAccessor.close();

	}
}