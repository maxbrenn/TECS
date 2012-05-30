package org.kit.tecs.zookeeper;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogUtil {

	static Logger logger;

	public LogUtil(Class callingClass) {

		logger = Logger.getLogger(callingClass);
		PropertyConfigurator.configure("conf/log4j.properties");

	}

}
