package org.kit.tecs.zookeeper;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;


public class DataWatcher implements Watcher {
	LogUtil zkLogger = new LogUtil(this.getClass());
  
  private ZooKeeperConnector dataAccessor;
  private String path;
  
  
  
  public DataWatcher(String hosts , String path) throws IOException, InterruptedException {
//    dataAccessor = new ZooKeeperConnector();
//    dataAccessor.connect(hosts);
    this.path = path;
    
  }
  
  public void displayConfig() throws InterruptedException, KeeperException {
    String value = dataAccessor.read(path, this);
    System.out.printf("Read %s as %s\n", path, value);
  }

  @Override
  public void process(WatchedEvent event) {
    if (event.getType() == EventType.NodeDataChanged) {
      try {
        displayConfig();
      } catch (InterruptedException e) {
        System.err.println("Interrupted. Exiting.");
        Thread.currentThread().interrupt();
      } catch (KeeperException e) {
        System.err.printf("KeeperException: %s. Exiting.\n", e);
      }
    }
  }
  
  public static void main(String[] args) throws Exception {
  
	  
	  Properties properties = new Properties();
		try {
			properties.loadFromXML(new FileInputStream("conf/zookeeper.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}  
	  
	  
	  
	DataWatcher configWatcher = new DataWatcher(properties.getProperty("zk.hosts"),properties.getProperty("zk.directory.nodestatus"));
    configWatcher.displayConfig();
    

    Thread.sleep(Long.MAX_VALUE);
  }
}