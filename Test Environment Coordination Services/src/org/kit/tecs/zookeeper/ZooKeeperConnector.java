package org.kit.tecs.zookeeper;

import java.nio.charset.Charset;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;
import org.kit.tecs.test.ClusterControlTest;

public class ZooKeeperConnector extends ConnectionHandler implements Watcher {
	LogUtil zkLogger = new LogUtil(this.getClass());
	
	

	public ZooKeeperConnector(ZooKeeperCluster zookeeperCluster) {

		super(zookeeperCluster.getHostsList());

	}

	public ZooKeeperConnector(String _hostsList) {

		super(_hostsList);

	}

	private void createPath(String _path) {

		String[] subPath = _path.split("\\/");
		String createPath = "";

		for (int i = 1; i < subPath.length; i++) {

			createPath = createPath + "/" + subPath[i];

			try {
				if (zk.exists(createPath, false) == null) {
					zk.create(createPath, null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				}
			} catch (KeeperException | InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	public void writeNodeData(String _path, String _value, boolean _isPersistent) {

		CreateMode createMode = null;
		if (_isPersistent) {
			createMode = CreateMode.PERSISTENT;
		} else {
			createMode = CreateMode.EPHEMERAL;
		}

		createPath(_path);

		System.exit(1);

		try {
			if (zk.exists(_path, false) == null) {

				zk.create(_path, _value.getBytes(Charset.forName("UTF-8")),
						Ids.OPEN_ACL_UNSAFE, createMode);

			} else {

				zk.setData(_path, _value.getBytes(Charset.forName("UTF-8")), -1);

			}

		} catch (KeeperException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String read(String path, Watcher watcher)
			throws InterruptedException, KeeperException {

		if (zk.exists(path, false) == null) {
			return "Node does not exists! Check path: " + path;
		} else {
			byte[] data = zk.getData(path, watcher, null);

			if (data.length > 0) {
				return new String(data, Charset.forName("UTF-8"));
			} else {
				return "No data in node: " + path;
			}

		}
	}

	public void observerNode(String path, ClusterControlTest.ControllerOperation op) {
		
		try {
			zk.exists(path, this);
		} catch (KeeperException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			op.execute();
		}
		
		
	}

	@Override
	  public void process(WatchedEvent event) {
	    if (event.getType() == EventType.NodeDataChanged) {
	    	System.out.println("Node Data Change Event");
	    	
	    	
	    	
	    	
	    }
	  }
	
}