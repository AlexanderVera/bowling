package org.jobsity.run.logic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.jobsity.run.interfaces.IMessages;

public class Messages implements IMessages{
	
	private InputStream messagesFile;
	private Properties messageProperties;
	private static final Logger log = Logger.getLogger(Main.class.getName());
	
	public Messages(){
		messagesFile = Thread.currentThread().getContextClassLoader()
		.getResourceAsStream("message.properties");
		messageProperties = new Properties();
		try{
			messageProperties.load(messagesFile);
		}
		catch(IOException exc){
			log.error(exc.getMessage());
		}
	}
	
	public String getMessage(String key) {
		return messageProperties.getProperty(key);
	}

}
