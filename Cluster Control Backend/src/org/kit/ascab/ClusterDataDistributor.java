package org.kit.ascab;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import org.apache.zookeeper.KeeperException;
import org.kit.ascab.data.NodeList;
import org.kit.ascab.zookeeper.ZooKeeperConnector;

public class ClusterDataDistributor {

	private ZooKeeperConnector zookeeperConnector;
	
	
	public ClusterDataDistributor(ZooKeeperConnector _zookeeperConnector) {
		zookeeperConnector = _zookeeperConnector;
	}
	
	
	public void distributeDataFile(String _localFilePath , String _remoteFilePath,NodeList _nodeList) {
		try {
		
					BufferedReader fileReader = new BufferedReader(new FileReader(_localFilePath));
				String line = "";
				String text = "";
				
			while(line != null) {
				
				
					line = fileReader.readLine();
				
				text = text + line + "\n";
				
				
			}
			
			
			zookeeperConnector.writeNodeData(_remoteFilePath, text,false);
				
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
