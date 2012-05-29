package org.kit.ascab.zookeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;

public class ConnectionHandler implements Watcher {
	LogUtil zkLogger = new LogUtil(this.getClass());

	private static final int SESSION_TIMEOUT = 5000;

	protected ZooKeeper zk;
	private CountDownLatch connectedSignal = new CountDownLatch(1);

	public ConnectionHandler(String hosts) {

		try {

			zk = new ZooKeeper(hosts, SESSION_TIMEOUT, this);
			connectedSignal.await();

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

//	public void connect(String hosts) throws IOException, InterruptedException {
//		zk = new ZooKeeper(hosts, SESSION_TIMEOUT, this);
//		connectedSignal.await();
//	}

	@Override
	public void process(WatchedEvent event) {
		
		
		if (event.getState() == KeeperState.SyncConnected) {
			
			connectedSignal.countDown();
		}
	}

	public void close() throws InterruptedException {
		zk.close();
	}
}